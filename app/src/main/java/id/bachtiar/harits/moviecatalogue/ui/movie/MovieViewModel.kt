package id.bachtiar.harits.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
) : ViewModel() {

    fun getPopularMovies(query: String): LiveData<DataResult<PagedList<MoviesEntity>>> =
        repo.getPopularMovies(query = query)

    fun getFavoriteMovies(query: String): LiveData<PagedList<MoviesEntity>> =
        repo.getFavoriteMoviesWithQuery(query)

    fun updateFavorite(movie: MoviesEntity) {
        repo.updateFavoriteMovie(movie)
    }
}