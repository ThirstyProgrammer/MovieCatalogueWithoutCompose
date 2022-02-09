package id.bachtiar.harits.moviecatalogue.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity

@Dao
interface MovieCatalogueDao {

    @RawQuery(observedEntities = [MoviesEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM MoviesEntity WHERE isFavourite")
    fun getFavoriteMovies(): List<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesList(movies: List<MoviesEntity>)

    @Query("SELECT * FROM MovieEntity WHERE movieId = :id")
    fun getMovie(id: Int) : LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovies(movie: MoviesEntity)

    @RawQuery(observedEntities = [TvShowsEntity::class])
    fun getTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM TvShowsEntity WHERE isFavourite")
    fun getFavoriteTvShows(): List<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowsList(tvShows: List<TvShowsEntity>)

    @Update
    suspend fun updateTvShows(tvShows: TvShowsEntity)

    @Query("SELECT * FROM TvShowEntity WHERE tvShowId = :id")
    fun getTvShow(id: Int) : LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShowEntity: TvShowEntity)
}