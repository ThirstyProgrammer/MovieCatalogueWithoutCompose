package id.bachtiar.harits.moviecatalogue.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.base.BaseViewModel
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.repository.MovieCatalogueRepository
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
) : BaseViewModel() {

    private val _response: MutableLiveData<Movie> = MutableLiveData()
    val response: LiveData<Movie> = _response

    fun getMovie(id: Int) {
        requestAPI(_response) {
            repo.getMovie(id)
        }
    }
}