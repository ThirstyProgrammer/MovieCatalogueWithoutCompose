package id.bachtiar.harits.moviecatalogue.ui.movie

import id.bachtiar.harits.moviecatalogue.model.Movie
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movies: List<Movie>

    @Before
    fun init() {
        movieViewModel = MovieViewModel()
        movies = listOf(Movie(), Movie())
    }

    @Test
    fun getMovies() {
        assertNotNull(movieViewModel.movies)
    }

    @Test
    fun setMovies() {
        movieViewModel.movies.addAll(movies)
        assertEquals(movies, movieViewModel.movies)
    }

    @Test
    fun checkMoviesSize() {
        movieViewModel.movies.addAll(movies)
        assertEquals(movies.size, movieViewModel.movies.size)
    }
}