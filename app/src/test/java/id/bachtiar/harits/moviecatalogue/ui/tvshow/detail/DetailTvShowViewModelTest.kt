package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.model.TvShow
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
        dummyResponse = DataDummy.getTvShow()
    }

    @Test
    fun getTvShow() {
        val tvShowResponse = MutableLiveData<DataResult<TvShow>>()
        tvShowResponse.value = dummyResponse
        `when`(movieCatalogueRepository.getTvShow(1)).thenReturn(tvShowResponse)
        val response = viewModel.getTvShow(1).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getTvShow(1)
        assertNotNull(response)
        assertEquals(tvShowResponse.value?.status, response.status)
        assertEquals(tvShowResponse.value?.message, response.message)
        assertEquals(tvShowResponse.value?.data, response.data)

        viewModel.getTvShow(1).observeForever(observer)
        verify(observer).onChanged(viewModel.getTvShow(1).value)
    }
}