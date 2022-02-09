package id.bachtiar.harits.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity

interface DataSource {

    fun getPopularMovies(page: Int = 1, query: String, isFavorite: Boolean): LiveData<DataResult<PagedList<MoviesEntity>>>
    suspend fun updateFavoriteMovie(movie: MoviesEntity)
    fun getPopularTvShows(page: Int = 1, query: String, isFavorite: Boolean): LiveData<DataResult<PagedList<TvShowsEntity>>>
    suspend fun updateFavoriteTvShows(tvShow: TvShowsEntity)
    fun getMovie(id:Int): LiveData<DataResult<MovieEntity>>
    fun getTvShow(id:Int): LiveData<DataResult<TvShowEntity>>
}