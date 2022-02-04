package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.model.TvShows
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
): ViewModel() {

    fun getPopularTvShows(): LiveData<DataResult<TvShows.Response>> = repo.getPopularTvShows()
}