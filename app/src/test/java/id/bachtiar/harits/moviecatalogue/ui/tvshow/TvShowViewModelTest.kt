package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.ViewState
import id.bachtiar.harits.moviecatalogue.model.TvShows
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
class TvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var viewModel: TvShowViewModel
    private lateinit var dummyResponse: DataResult<TvShows.Response>
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<DataResult<TvShows.Response>> = mock()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
        dummyResponse = DataResult(
            ViewState.SUCCESS,
            TvShows.Response(
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
            ),
            ""
        )
    }

    @Test
    fun getPopularTvShows()  {
        val dummyResult = MutableLiveData<DataResult<TvShows.Response>>()
        dummyResult.value = dummyResponse
        `when`(movieCatalogueRepository.getPopularTvShows(1)).thenReturn(dummyResult)
        val response = viewModel.getPopularTvShows().getOrAwaitValueTest()
        verify(movieCatalogueRepository).getPopularTvShows(1)
        assertNotNull(response)
        assertEquals(dummyResponse.status, response.status)
        assertEquals(dummyResponse.message, response.message)
        assertEquals(dummyResponse.data, response.data)

        viewModel.getPopularTvShows().observeForever(observer)
        verify(observer).onChanged(viewModel.getPopularTvShows().value)
    }
}