package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.model.TvShows
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
        dummyResponse = DataDummy.getTvShows()
    }

    @Test
    fun getPopularTvShows()  {
        val tvShowsResponse = MutableLiveData<DataResult<TvShows.Response>>()
        tvShowsResponse.value = dummyResponse
        `when`(movieCatalogueRepository.getPopularTvShows(1)).thenReturn(tvShowsResponse)
        val response = viewModel.getPopularTvShows().getOrAwaitValueTest()
        verify(movieCatalogueRepository).getPopularTvShows(1)
        assertNotNull(response)
        assertEquals(tvShowsResponse.value?.status, response.status)
        assertEquals(tvShowsResponse.value?.message, response.message)
        assertEquals(tvShowsResponse.value?.data, response.data)

        viewModel.getPopularTvShows().observeForever(observer)
        verify(observer).onChanged(viewModel.getPopularTvShows().value)
    }
}