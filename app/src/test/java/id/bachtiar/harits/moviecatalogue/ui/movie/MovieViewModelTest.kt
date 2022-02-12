package id.bachtiar.harits.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.util.DataDummy
import id.bachtiar.harits.moviecatalogue.utils.getOrAwaitValueTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var viewModel: MovieViewModel

    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<DataResult<PagedList<MoviesEntity>>> = mock()
    private var observerFavorite: Observer<PagedList<MoviesEntity>> = mock()
    private var pagedList: PagedList<MoviesEntity> = mock()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getPopularMovies() {
        val query = "QUERY"
        val moviesDatabase = DataResult.success(pagedList)
        `when`(moviesDatabase.data?.size).thenReturn(5)
        val moviesResponse = MutableLiveData<DataResult<PagedList<MoviesEntity>>>()
        moviesResponse.value = moviesDatabase

        `when`(movieCatalogueRepository.getPopularMovies(1, query)).thenReturn(moviesResponse)
        val moviesEntity = viewModel.getPopularMovies(query).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getPopularMovies(1, query)
        assertNotNull(moviesEntity)
        assertEquals(moviesResponse.value?.status, moviesEntity.status)
        assertEquals(moviesResponse.value?.message, moviesEntity.message)
        assertEquals(moviesResponse.value?.data, moviesEntity.data)
        assertEquals(5, moviesEntity.data?.size)

        viewModel.getPopularMovies(query).observeForever(observer)
        verify(observer).onChanged(viewModel.getPopularMovies(query).value)
    }

    @Test
    fun getFavoriteMovies() {
        val query = "QUERY"
        val favoriteMovies = pagedList
        `when`(favoriteMovies.size).thenReturn(5)
        val movies= MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = favoriteMovies

        `when`(movieCatalogueRepository.getFavoriteMoviesWithQuery(query)).thenReturn(movies)
        val moviesEntity = viewModel.getFavoriteMovies(query).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getFavoriteMoviesWithQuery(query)
        assertNotNull(moviesEntity)
        assertEquals(5, moviesEntity.size)

        viewModel.getFavoriteMovies(query).observeForever(observerFavorite)
        verify(observerFavorite).onChanged(viewModel.getFavoriteMovies(query).value)
    }

    @Test
    fun updateFavorite() {
        viewModel.updateFavorite(DataDummy.generateSelectedMovies())
        verify(movieCatalogueRepository).updateFavoriteMovie(DataDummy.generateSelectedMovies())
        verifyNoMoreInteractions(movieCatalogueRepository)
    }
}