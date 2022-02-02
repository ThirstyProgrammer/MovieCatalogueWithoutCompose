package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.base.BaseViewModel
import id.bachtiar.harits.moviecatalogue.model.TvShows
import id.bachtiar.harits.moviecatalogue.repository.MovieCatalogueRepository
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
): BaseViewModel() {

    private val _response: MutableLiveData<TvShows.Response> = MutableLiveData()
    val response: LiveData<TvShows.Response> = _response

    fun getPopularTvShows() {
        requestAPI(_response) {
            repo.getPopularTvShows()
        }
    }
}