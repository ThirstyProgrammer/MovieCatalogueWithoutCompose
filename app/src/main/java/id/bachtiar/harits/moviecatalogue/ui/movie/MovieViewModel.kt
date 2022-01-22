package id.bachtiar.harits.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.model.Movie
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(): ViewModel() {

    var movies = arrayListOf<Movie>()
}