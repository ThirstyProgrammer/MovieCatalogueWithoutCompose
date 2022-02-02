package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.base.BaseViewModel
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.repository.MovieCatalogueRepository
import javax.inject.Inject

@HiltViewModel
class DetailTvShowViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
) : BaseViewModel() {

    private val _response: MutableLiveData<TvShow> = MutableLiveData()
    val response: LiveData<TvShow> = _response

    fun getTvShow(id: Int) {
        requestAPI(_response) {
            repo.getTvShow(id)
        }
    }
}