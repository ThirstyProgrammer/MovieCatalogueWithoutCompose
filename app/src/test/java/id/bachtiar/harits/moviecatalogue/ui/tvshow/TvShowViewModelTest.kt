package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
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
    private val movieCatalogueRepository: MovieCatalogueRepository = mock()
    private var observer: Observer<DataResult<PagedList<TvShowsEntity>>> = mock()
    private var pagedList: PagedList<TvShowsEntity> = mock()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun getPopularTvShows()  {
        val queryAndFavorite = Pair("", false)
        val tvShowsDatabase = DataResult.success(pagedList)
        `when`(tvShowsDatabase.data?.size).thenReturn(5)
        val tvShowsResponse = MutableLiveData<DataResult<PagedList<TvShowsEntity>>>()
        tvShowsResponse.value = tvShowsDatabase

        `when`(movieCatalogueRepository.getPopularTvShows(1, queryAndFavorite)).thenReturn(tvShowsResponse)
        val tvShowsEntity = viewModel.getPopularTvShows(queryAndFavorite).getOrAwaitValueTest()
        verify(movieCatalogueRepository).getPopularTvShows(1, queryAndFavorite)
        assertNotNull(tvShowsEntity)
        assertEquals(tvShowsResponse.value?.status, tvShowsEntity.status)
        assertEquals(tvShowsResponse.value?.message, tvShowsEntity.message)
        assertEquals(tvShowsResponse.value?.data, tvShowsEntity.data)
        assertEquals(5, tvShowsEntity.data?.size)

        viewModel.getPopularTvShows(queryAndFavorite).observeForever(observer)
        verify(observer).onChanged(viewModel.getPopularTvShows(queryAndFavorite).value)
    }

    @Test
    fun updateFavorite() {
        viewModel.updateFavorite(DataDummy.generateSelectedTvShows())
        verify(movieCatalogueRepository).updateFavoriteTvShows(DataDummy.generateSelectedTvShows())
        verifyNoMoreInteractions(movieCatalogueRepository)
    }
}