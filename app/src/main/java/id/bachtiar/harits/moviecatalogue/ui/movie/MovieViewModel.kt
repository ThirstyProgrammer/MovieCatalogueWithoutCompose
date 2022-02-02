package id.bachtiar.harits.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.base.BaseViewModel
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.repository.MovieCatalogueRepository
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
) : BaseViewModel() {

    private val _response: MutableLiveData<Movies.Response> = MutableLiveData()
    val response: LiveData<Movies.Response> = _response

    fun getPopularMovies() {
        requestAPI(_response) {
            repo.getPopularMovies()
        }
    }
}