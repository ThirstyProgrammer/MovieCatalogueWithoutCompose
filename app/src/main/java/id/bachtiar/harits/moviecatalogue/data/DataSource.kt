package id.bachtiar.harits.moviecatalogue.data

import androidx.lifecycle.LiveData
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.model.TvShows

interface DataSource {

    fun getPopularMovies(page: Int = 1): LiveData<DataResult<Movies.Response>>
    fun getPopularTvShows(page: Int = 1): LiveData<DataResult<TvShows.Response>>
    fun getMovie(id:Int): LiveData<DataResult<Movie>>
    fun getTvShow(id:Int): LiveData<DataResult<TvShow>>
}