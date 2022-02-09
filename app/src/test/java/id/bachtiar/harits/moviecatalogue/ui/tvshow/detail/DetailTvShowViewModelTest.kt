package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
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
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<DataResult<TvShowEntity>> = mock()

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTvShow() {
        val responseDataResult = DataResult.success(DataDummy.generateSelectedTvShow())
        val tvShow = MutableLiveData<DataResult<TvShowEntity>>()
        tvShow.value = responseDataResult
        `when`(movieCatalogueRepository.getTvShow(1)).thenReturn(tvShow)
        val response = viewModel.getTvShow(1).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getTvShow(1)
        assertNotNull(response)
        assertEquals(tvShow.value?.status, response.status)
        assertEquals(tvShow.value?.message, response.message)
        assertEquals(tvShow.value?.data?.tvShowId, response.data?.tvShowId)
        assertEquals(tvShow.value?.data?.title, response.data?.title)
        assertEquals(tvShow.value?.data?.overview, response.data?.overview)
        assertEquals(tvShow.value?.data?.firstAirDate, response.data?.firstAirDate)
        assertEquals(tvShow.value?.data?.poster, response.data?.poster)
        assertEquals(tvShow.value?.data?.rating, response.data?.rating)
        assertEquals(tvShow.value?.data?.totalUserRating, response.data?.totalUserRating)
        assertEquals(tvShow.value?.data?.productionCompanies, response.data?.productionCompanies)
        assertEquals(tvShow.value?.data?.genres, response.data?.genres)
        assertEquals(tvShow.value?.data?.seasons, response.data?.seasons)

        viewModel.getTvShow(1).observeForever(observer)
        verify(observer).onChanged(viewModel.getTvShow(1).value)
    }
}