package id.bachtiar.harits.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.bachtiar.harits.moviecatalogue.data.local.LocalDataSource
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
import id.bachtiar.harits.moviecatalogue.data.remote.RemoteDataSource
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.model.TvShows
import id.bachtiar.harits.moviecatalogue.util.AppExecutors

class FakeMovieCatalogueRepository(
    private val remotedDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): DataSource {

    override fun getPopularMovies(page: Int, query: String, isFavorite: Boolean): LiveData<DataResult<PagedList<MoviesEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFavoriteMovie(movie: MoviesEntity) {
        TODO("Not yet implemented")
    }

    override fun getPopularTvShows(page: Int, query: String, isFavorite: Boolean): LiveData<DataResult<PagedList<TvShowsEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFavoriteTvShows(tvShow: TvShowsEntity) {
        TODO("Not yet implemented")
    }

    override fun getMovie(id: Int): LiveData<DataResult<MovieEntity>> {
        TODO("Not yet implemented")
    }

    override fun getTvShow(id: Int): LiveData<DataResult<TvShowEntity>> {
        TODO("Not yet implemented")
    }

//    override fun getPopularMovies(page: Int): LiveData<DataResult<Movies.Response>> {
//        return remotedDataSource.getPopularMovies(page)
//    }
//
//    override fun getPopularTvShows(page: Int): LiveData<DataResult<TvShows.Response>> {
//        return remotedDataSource.getPopularTvShows(page)
//    }
//
//    override fun getMovie(id: Int): LiveData<DataResult<Movie>> {
//        return remotedDataSource.getMovie(id)
//    }
//
//    override fun getTvShow(id: Int): LiveData<DataResult<TvShow>> {
//        return remotedDataSource.getTvShow(id)
//    }
}