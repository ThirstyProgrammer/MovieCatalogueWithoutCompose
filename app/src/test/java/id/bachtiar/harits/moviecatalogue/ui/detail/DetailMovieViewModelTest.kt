package id.bachtiar.harits.moviecatalogue.ui.detail

import id.bachtiar.harits.moviecatalogue.model.Movie
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private lateinit var movie: Movie

    @Before
    fun init() {
        detailMovieViewModel = DetailMovieViewModel()
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
        assertNotNull(detailMovieViewModel.movie)
    }

    @Test
    fun setMovie() {
        detailMovieViewModel.movie = movie
        assertEquals(movie, detailMovieViewModel.movie)
    }
}