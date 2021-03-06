package id.bachtiar.harits.moviecatalogue.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
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
class DetailMovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var viewModel: DetailMovieViewModel
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<DataResult<MovieEntity>> = mock()

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMovie() {
        val responseDataResult = DataResult.success(DataDummy.generateSelectedMovie())
        val movie = MutableLiveData<DataResult<MovieEntity>>()
        movie.value = responseDataResult
        `when`(movieCatalogueRepository.getMovie(1)).thenReturn(movie)
        val response = viewModel.getMovie(1).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getMovie(1)
        assertNotNull(response)
        assertEquals(movie.value?.status, response.status)
        assertEquals(movie.value?.message, response.message)
        assertEquals(movie.value?.data?.movieId, response.data?.movieId)
        assertEquals(movie.value?.data?.title, response.data?.title)
        assertEquals(movie.value?.data?.overview, response.data?.overview)
        assertEquals(movie.value?.data?.releaseDate, response.data?.releaseDate)
        assertEquals(movie.value?.data?.poster, response.data?.poster)
        assertEquals(movie.value?.data?.rating, response.data?.rating)
        assertEquals(movie.value?.data?.totalUserRating, response.data?.totalUserRating)
        assertEquals(movie.value?.data?.productionCompanies, response.data?.productionCompanies)
        assertEquals(movie.value?.data?.genres, response.data?.genres)

        viewModel.getMovie(1).observeForever(observer)
        verify(observer).onChanged(viewModel.getMovie(1).value)    }
}