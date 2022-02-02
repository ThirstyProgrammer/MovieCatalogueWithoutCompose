package id.bachtiar.harits.moviecatalogue.ui.movie

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.base.BaseViewModelTest
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.repository.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.ui.movie.detail.DetailMovieViewModel
import id.bachtiar.harits.moviecatalogue.utils.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var dummyResponse: Movies.Response
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<Movies.Response> = mock()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieCatalogueRepository)
        dummyResponse = Movies.Response(
            page = 1,
            totalPages = 32141,
            results = listOf(
                Movies.Data(
                    id = 634649,
                    title = "Spider-Man: No Way Home",
                    poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                    releaseDate = "2021-12-15",
                    overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                ),
                Movies.Data(
                    id = 524434,
                    title = "Spider-Man: No Way Home",
                    poster = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                    releaseDate = "2021-11-03",
                    overview = "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                ),
                Movies.Data(
                    id = 585083,
                    title = "Hotel Transylvania: Transformania",
                    poster = "/teCy1egGQa0y8ULJvlrDHQKnxBL.jpg",
                    releaseDate = "2022-01-13",
                    overview = "When Van Helsing's mysterious invention, the \\\"Monsterfication Ray,\\\" goes haywire, Drac and his monster pals are all transformed into humans, and Johnny becomes a monster. In their new mismatched bodies, Drac and Johnny must team up and race across the globe to find a cure before it's too late, and before they drive each other crazy.",
                ),
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPopularMovies() = runBlockingTest {
        `when`(movieCatalogueRepository.getPopularMovies(1)).thenReturn(dummyResponse)
        viewModel.getPopularMovies()
        val response = viewModel.response.getOrAwaitValueTest()
        assertNotNull(response)
        assertEquals(dummyResponse, response)
        assertEquals(dummyResponse.results?.size, response.results?.size)

        viewModel.response.observeForever(observer)
        verify(observer).onChanged(viewModel.response.value)
    }
}