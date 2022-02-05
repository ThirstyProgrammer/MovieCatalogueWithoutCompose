package id.bachtiar.harits.moviecatalogue.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.model.Movie
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
    private lateinit var dummyResponse: DataResult<Movie>
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<DataResult<Movie>> = mock()

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
        dummyResponse = DataDummy.getMovie()
    }

    @Test
    fun getMovie() {
        val movieResponse = MutableLiveData<DataResult<Movie>>()
        movieResponse.value = dummyResponse
        `when`(movieCatalogueRepository.getMovie(1)).thenReturn(movieResponse)
        val response = viewModel.getMovie(1).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getMovie(1)
        assertNotNull(response)
        assertEquals(movieResponse.value?.status, response.status)
        assertEquals(movieResponse.value?.message, response.message)
        assertEquals(movieResponse.value?.data, response.data)

        viewModel.getMovie(1).observeForever(observer)
        verify(observer).onChanged(viewModel.getMovie(1).value)
    }
}