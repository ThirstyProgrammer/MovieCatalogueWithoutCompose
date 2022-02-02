package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.base.BaseViewModelTest
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.model.TvShows
import id.bachtiar.harits.moviecatalogue.repository.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.ui.movie.MovieViewModel
import id.bachtiar.harits.moviecatalogue.ui.movie.detail.DetailMovieViewModel
import id.bachtiar.harits.moviecatalogue.utils.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var dummyResponse: TvShows.Response
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<TvShows.Response> = mock()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
        dummyResponse = TvShows.Response(
            page = 1,
            totalPages = 6192,
            results = listOf(
                TvShows.Data(
                    id = 85552,
                    title = "Euphoria",
                    overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                    firstAirDate = "2019-06-16",
                    poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg"
                ),
                TvShows.Data(
                    id = 110492,
                    title = "Peacemaker",
                    overview = "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.”",
                    firstAirDate = "2022-01-13",
                    poster = "/hE3LRZAY84fG19a18pzpkZERjTE.jpg"
                ),
                TvShows.Data(
                    id = 115036,
                    title = "The Book of Boba Fett",
                    overview = "Legendary bounty hunter Boba Fett and mercenary Fennec Shand must navigate the galaxy’s underworld when they return to the sands of Tatooine to stake their claim on the territory once ruled by Jabba the Hutt and his crime syndicate.",
                    firstAirDate = "2021-12-29",
                    poster = "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg"
                ),
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPopularTvShows() = runBlockingTest {
        Mockito.`when`(movieCatalogueRepository.getPopularTvShows(1)).thenReturn(dummyResponse)
        viewModel.getPopularTvShows()
        val response = viewModel.response.getOrAwaitValueTest()
        assertNotNull(response)
        assertEquals(dummyResponse, response)
        assertEquals(dummyResponse.results?.size, response.results?.size)

        viewModel.response.observeForever(observer)
        verify(observer).onChanged(viewModel.response.value)
    }
}