package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.ViewState
import id.bachtiar.harits.moviecatalogue.model.Genres
import id.bachtiar.harits.moviecatalogue.model.ProductionCompanies
import id.bachtiar.harits.moviecatalogue.model.Seasons
import id.bachtiar.harits.moviecatalogue.model.TvShow
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
class DetailTvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var viewModel: DetailTvShowViewModel
    private lateinit var dummyResponse: DataResult<TvShow>
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<DataResult<TvShow>> = mock()

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(movieCatalogueRepository)
        dummyResponse = DataResult(
            ViewState.SUCCESS,
            TvShow(
                title = "Euphoria",
                poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                firstAirDate = "2019-06-16",
                rating = 8.4,
                totalUserRating = 6006,
                productionCompanies = listOf(
                    ProductionCompanies(
                        id = 41077,
                        name = "A24"
                    ),
                    ProductionCompanies(
                        id = 139809,
                        name = "The Reasonable Bunch"
                    ),
                    ProductionCompanies(
                        id = 126587,
                        name = "Little Lamb Productions"
                    ),
                    ProductionCompanies(
                        id = 124433,
                        name = "DreamCrew"
                    ),
                ),
                genres = listOf(
                    Genres(
                        id = 18,
                        name = "Drama"
                    )
                ),
                seasons = listOf(
                    Seasons(
                        name = "Specials",
                        poster = "/6x31o4AjfVxakRJ0OQpFH8f9Kzb.jpg",
                        airDate = "2020-12-06",
                        episodeCount = 2,
                        seasonNumber = 0
                    ),
                    Seasons(
                        name = "Season 1",
                        poster = "/5mi3aRl16yKmfpQJMzvqN5TXkdA.jpg",
                        airDate = "2019-06-16",
                        episodeCount = 8,
                        seasonNumber = 1
                    ),
                    Seasons(
                        name = "Season 2",
                        poster = "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                        airDate = "2022-01-09",
                        episodeCount = 8,
                        seasonNumber = 2
                    ),
                ),
                overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media."
            ),
            ""
        )
    }

    @Test
    fun getTvShow() {
        val dummyResult = MutableLiveData<DataResult<TvShow>>()
        dummyResult.value = dummyResponse
        `when`(movieCatalogueRepository.getTvShow(1)).thenReturn(dummyResult)
        val response = viewModel.getTvShow(1).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getTvShow(1)
        assertNotNull(response)
        assertEquals(dummyResponse.status, response.status)
        assertEquals(dummyResponse.message, response.message)
        assertEquals(dummyResponse.data, response.data)

        viewModel.getTvShow(1).observeForever(observer)
        verify(observer).onChanged(viewModel.getTvShow(1).value)
    }
}