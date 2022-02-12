package id.bachtiar.harits.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
import id.bachtiar.harits.moviecatalogue.data.local.room.MovieCatalogueDao
import id.bachtiar.harits.moviecatalogue.util.FilterAndSearchUtils
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val movieCatalogueDao: MovieCatalogueDao) {

    fun getMovies(query: String): DataSource.Factory<Int, MoviesEntity> = movieCatalogueDao.getMovies(FilterAndSearchUtils.getMovies(query))
    fun getFavoriteMoviesWithQuery(query: String): DataSource.Factory<Int, MoviesEntity> = movieCatalogueDao.getFavoriteMoviesWithQuery(FilterAndSearchUtils.getFavoriteMovies(query = query))
    fun getFavoriteMovies(): List<MoviesEntity> = movieCatalogueDao.getFavoriteMovies()
    fun insertMovies(movies: List<MoviesEntity>) = movieCatalogueDao.insertMoviesList(movies)
    fun updateMovies(movie: MoviesEntity) = movieCatalogueDao.updateMovies(movie)
    fun getMovie(id: Int): LiveData<MovieEntity> = movieCatalogueDao.getMovie(id)

    fun insertMovie(movieEntity: MovieEntity) = movieCatalogueDao.insertMovie(movieEntity)
    fun getTvShows(query: String): DataSource.Factory<Int, TvShowsEntity> = movieCatalogueDao.getTvShows(FilterAndSearchUtils.getTvShows(query = query))

    fun getFavoriteTvShows(): List<TvShowsEntity> = movieCatalogueDao.getFavoriteTvShows()
    fun getFavoriteTvShowsWithQuery(query: String): DataSource.Factory<Int, TvShowsEntity> = movieCatalogueDao.getFavoriteTvShowsWithQuery(FilterAndSearchUtils.getFavoriteTvShows(query = query))
    fun insertTvShows(tvShows: List<TvShowsEntity>) = movieCatalogueDao.insertTvShowsList(tvShows)
    fun updateTvShows(tvShow: TvShowsEntity) = movieCatalogueDao.updateTvShows(tvShow)

    fun getTvShow(id: Int): LiveData<TvShowEntity> = movieCatalogueDao.getTvShow(id)
    fun insertTvShow(tvShowEntity: TvShowEntity) = movieCatalogueDao.insertTvShow(tvShowEntity)
}