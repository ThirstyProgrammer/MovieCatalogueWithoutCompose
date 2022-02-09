package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import javax.inject.Inject

@HiltViewModel
class DetailTvShowViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
) : ViewModel() {

    fun getTvShow(id:Int) : LiveData<DataResult<TvShowEntity>> = repo.getTvShow(id)
}