package id.bachtiar.harits.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.model.Movie
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor() : ViewModel() {

    var movie: Movie = Movie()
}