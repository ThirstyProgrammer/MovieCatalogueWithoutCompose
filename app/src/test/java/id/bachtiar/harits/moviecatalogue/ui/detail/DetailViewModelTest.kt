package id.bachtiar.harits.moviecatalogue.ui.detail

import id.bachtiar.harits.moviecatalogue.model.Movie
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var movie: Movie

    @Before
    fun init() {
        detailViewModel = DetailViewModel()
        movie = Movie(
            title = "Spiderman",
            cover = "url_cover",
            description = "description spiderman",
            releaseDate = "release_date",
            rating = 90,
            totalUserRating = 100
        )
    }

    @Test
    fun getMovie() {
        assertNotNull(detailViewModel.movie)
    }

    @Test
    fun setMovie() {
        detailViewModel.movie = movie
        assertEquals(movie, detailViewModel.movie)
    }
}