package id.bachtiar.harits.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.model.TvShow
import javax.inject.Inject

@HiltViewModel
class DetailTvShowViewModel @Inject constructor() : ViewModel() {

    var tvShow: TvShow = TvShow()
}