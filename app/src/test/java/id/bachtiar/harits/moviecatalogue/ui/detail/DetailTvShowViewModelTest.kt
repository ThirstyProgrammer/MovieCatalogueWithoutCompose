package id.bachtiar.harits.moviecatalogue.ui.detail

import id.bachtiar.harits.moviecatalogue.model.TvShow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailTvShowViewModelTest {

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private lateinit var tvShow: TvShow

    @Before
    fun init() {
        detailTvShowViewModel = DetailTvShowViewModel()
        tvShow = TvShow(
            title = "Arrow",
            cover = "url_cover",
            description = "description Arrow",
            releaseDate = "release_date",
            rating = 90,
            totalUserRating = 100
        )
    }

    @Test
    fun getTvShow() {
        assertNotNull(detailTvShowViewModel.tvShow)
    }

    @Test
    fun setTvShow() {
        detailTvShowViewModel.tvShow = tvShow
        assertEquals(tvShow, detailTvShowViewModel.tvShow)
    }
}