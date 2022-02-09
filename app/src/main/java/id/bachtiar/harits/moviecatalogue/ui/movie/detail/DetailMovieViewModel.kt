package id.bachtiar.harits.moviecatalogue.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
) : ViewModel() {

    fun getMovie(id:Int) : LiveData<DataResult<MovieEntity>> = repo.getMovie(id)
}