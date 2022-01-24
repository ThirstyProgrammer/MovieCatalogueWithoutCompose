package id.bachtiar.harits.moviecatalogue.ui.detail

import id.bachtiar.harits.moviecatalogue.model.Cast
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.SubDesc
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
            totalUserRating = 100,
            category = listOf("Fantasy", "Action"),
            subDesc = listOf(SubDesc(title = "Director", description = "Sam Raimi")),
            cast = listOf(Cast(name = "Tobey Maguire", roleName = "Peter Parker", avatar = "url_avatar"))
        )
    }

    @Test
    fun getMovie() {
        detailMovieViewModel.movie = movie
        assertNotNull(detailMovieViewModel.movie)
        assertEquals(detailMovieViewModel.movie.title, movie.title)
        assertEquals(detailMovieViewModel.movie.cover, movie.cover)
        assertEquals(detailMovieViewModel.movie.description, movie.description)
        assertEquals(detailMovieViewModel.movie.releaseDate, movie.releaseDate)
        assertEquals(detailMovieViewModel.movie.rating, movie.rating)
        assertEquals(detailMovieViewModel.movie.totalUserRating, movie.totalUserRating)
        assertEquals(detailMovieViewModel.movie.category, movie.category)
        assertEquals(detailMovieViewModel.movie.subDesc, movie.subDesc)
        assertEquals(detailMovieViewModel.movie.cast, movie.cast)
    }

    @Test
    fun setMovie() {
        detailMovieViewModel.movie = movie
        assertEquals(movie, detailMovieViewModel.movie)
    }
}