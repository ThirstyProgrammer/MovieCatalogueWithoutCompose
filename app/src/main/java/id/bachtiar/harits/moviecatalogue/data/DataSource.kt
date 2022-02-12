package id.bachtiar.harits.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity

interface DataSource {

    fun getPopularMovies(page: Int = 1, query: String): LiveData<DataResult<PagedList<MoviesEntity>>>
    fun getFavoriteMoviesWithQuery(query: String): LiveData<PagedList<MoviesEntity>>
    fun updateFavoriteMovie(movie: MoviesEntity)
    fun getPopularTvShows(page: Int = 1, query: String): LiveData<DataResult<PagedList<TvShowsEntity>>>
    fun getFavoriteTvShowsWithQuery(query: String): LiveData<PagedList<TvShowsEntity>>
    fun updateFavoriteTvShows(tvShow: TvShowsEntity)
    fun getMovie(id: Int): LiveData<DataResult<MovieEntity>>
    fun getTvShow(id: Int): LiveData<DataResult<TvShowEntity>>
}