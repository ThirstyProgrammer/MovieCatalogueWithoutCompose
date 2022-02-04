package id.bachtiar.harits.moviecatalogue.data

import androidx.lifecycle.LiveData
import id.bachtiar.harits.moviecatalogue.data.remote.RemoteDataSource
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.model.TvShows
import javax.inject.Inject

class MovieCatalogueRepository @Inject constructor(private val remotedDataSource: RemoteDataSource) : DataSource {

    override fun getPopularMovies(page: Int): LiveData<DataResult<Movies.Response>> {
        return remotedDataSource.getPopularMovies(page)
    }

    override fun getPopularTvShows(page: Int): LiveData<DataResult<TvShows.Response>> {
        return remotedDataSource.getPopularTvShows(page)
    }

    override fun getMovie(id: Int): LiveData<DataResult<Movie>> {
        return remotedDataSource.getMovie(id)
    }

    override fun getTvShow(id: Int): LiveData<DataResult<TvShow>> {
        return remotedDataSource.getTvShow(id)
    }
}