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

    fun getPopularMovies(queryAndFavorite: Pair<String, Boolean>): LiveData<DataResult<PagedList<MoviesEntity>>> =
        repo.getPopularMovies(queryAndFavorite =  queryAndFavorite)

    fun updateFavorite(movie: MoviesEntity) {
        repo.updateFavoriteMovie(movie)
    }
}