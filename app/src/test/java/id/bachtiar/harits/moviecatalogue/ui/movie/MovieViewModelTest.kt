package id.bachtiar.harits.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.model.Movies
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
    private lateinit var dummyResponse: DataResult<Movies.Response>
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<DataResult<Movies.Response>> = mock()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieCatalogueRepository)
        dummyResponse = DataDummy.getMovies()
    }

    @Test
    fun getPopularMovies() {
        val moviesResponse = MutableLiveData<DataResult<Movies.Response>>()
        moviesResponse.value = dummyResponse
        `when`(movieCatalogueRepository.getPopularMovies(1)).thenReturn(moviesResponse)
        val response = viewModel.getPopularMovies().getOrAwaitValueTest()
        verify(movieCatalogueRepository).getPopularMovies(1)
        assertNotNull(response)
        assertEquals(moviesResponse.value?.status, response.status)
        assertEquals(moviesResponse.value?.message, response.message)
        assertEquals(moviesResponse.value?.data, response.data)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(viewModel.getPopularMovies().value)
    }
}