package id.bachtiar.harits.moviecatalogue.ui.tvshow

import id.bachtiar.harits.moviecatalogue.model.TvShow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var tvShows: List<TvShow>

    @Before
    fun init() {
        tvShowViewModel = TvShowViewModel()
        tvShows = listOf(TvShow(), TvShow())
    }

    @Test
    fun getTvShows() {
        assertNotNull(tvShowViewModel.tvShows)
    }

    @Test
    fun setTvShows() {
        tvShowViewModel.tvShows.addAll(tvShows)
        assertEquals(tvShows, tvShowViewModel.tvShows)
    }

    @Test
    fun checkTvShowsSize() {
        tvShowViewModel.tvShows.addAll(tvShows)
        assertEquals(tvShows.size, tvShowViewModel.tvShows.size)
    }
}