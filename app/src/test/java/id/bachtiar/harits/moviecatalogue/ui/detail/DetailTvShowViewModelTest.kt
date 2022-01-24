package id.bachtiar.harits.moviecatalogue.ui.detail

import id.bachtiar.harits.moviecatalogue.model.Cast
import id.bachtiar.harits.moviecatalogue.model.SubDesc
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
            totalUserRating = 100,
            category = listOf("Fantasy", "Adventure"),
            subDesc = listOf(SubDesc(title = "Creator", description = "Rowell")),
            cast = listOf(Cast(name = "Stephen Amell", roleName = "Oliver Queen", avatar = "url_avatar"))
        )
    }

    @Test
    fun getTvShow() {
        detailTvShowViewModel.tvShow = tvShow
        assertNotNull(detailTvShowViewModel.tvShow)
        assertNotNull(detailTvShowViewModel.tvShow)
        assertEquals(detailTvShowViewModel.tvShow.title, tvShow.title)
        assertEquals(detailTvShowViewModel.tvShow.cover, tvShow.cover)
        assertEquals(detailTvShowViewModel.tvShow.description, tvShow.description)
        assertEquals(detailTvShowViewModel.tvShow.releaseDate, tvShow.releaseDate)
        assertEquals(detailTvShowViewModel.tvShow.rating, tvShow.rating)
        assertEquals(detailTvShowViewModel.tvShow.totalUserRating, tvShow.totalUserRating)
        assertEquals(detailTvShowViewModel.tvShow.category, tvShow.category)
        assertEquals(detailTvShowViewModel.tvShow.subDesc, tvShow.subDesc)
        assertEquals(detailTvShowViewModel.tvShow.cast, tvShow.cast)
    }

    @Test
    fun setTvShow() {
        detailTvShowViewModel.tvShow = tvShow
        assertEquals(tvShow, detailTvShowViewModel.tvShow)
    }
}