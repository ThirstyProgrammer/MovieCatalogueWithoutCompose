package id.bachtiar.harits.moviecatalogue.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.ViewState
import id.bachtiar.harits.moviecatalogue.model.Genres
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.ProductionCompanies
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
        dummyResponse = DataResult(
            ViewState.SUCCESS,
            Movie(
                title = "Spider-Man: No Way Home",
                poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                releaseDate = "2021-12-1",
                rating = 8.4,
                totalUserRating = 6727,
                productionCompanies = listOf(
                    ProductionCompanies(
                        id = 420,
                        name = "Marvel Studios"
                    ),
                    ProductionCompanies(
                        id = 84041,
                        name = "Pascal Pictures"
                    ),
                    ProductionCompanies(
                        id = 5,
                        name = "Columbia Pictures"
                    )
                ),
                genres = listOf(
                    Genres(
                        id = 28,
                        name = "Action"
                    ),
                    Genres(
                        id = 12,
                        name = "Adventure"
                    ),
                    Genres(
                        id = 878,
                        name = "Science Fiction"
                    ),
                ),
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            ),
            ""
        )
    }

    @Test
    fun getMovie() {
        val dummyResult = MutableLiveData<DataResult<Movie>>()
        dummyResult.value = dummyResponse
        `when`(movieCatalogueRepository.getMovie(1)).thenReturn(dummyResult)
        val response = viewModel.getMovie(1).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getMovie(1)
        assertNotNull(response)
        assertEquals(dummyResponse.status, response.status)
        assertEquals(dummyResponse.message, response.message)
        assertEquals(dummyResponse.data, response.data)

        viewModel.getMovie(1).observeForever(observer)
        verify(observer).onChanged(viewModel.getMovie(1).value)
    }
}