package id.bachtiar.harits.moviecatalogue.ui.movie.detail

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.base.BaseViewModelTest
import id.bachtiar.harits.moviecatalogue.model.Genres
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.ProductionCompanies
import id.bachtiar.harits.moviecatalogue.repository.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.utils.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var dummyMovie: Movie
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<Movie> = mock()

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
        dummyMovie = Movie(
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

            )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getMovie() = runBlockingTest {
        `when`(movieCatalogueRepository.getMovie(1)).thenReturn(dummyMovie)
        viewModel.getMovie(1)
        val movie = viewModel.response.getOrAwaitValueTest()
        assertNotNull(movie)
        assertEquals(dummyMovie, movie)

        viewModel.response.observeForever(observer)
        verify(observer).onChanged(viewModel.response.value)
    }
}