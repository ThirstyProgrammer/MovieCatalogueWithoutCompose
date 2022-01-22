package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.model.TvShow
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(): ViewModel() {

    var tvShows = arrayListOf<TvShow>()
}