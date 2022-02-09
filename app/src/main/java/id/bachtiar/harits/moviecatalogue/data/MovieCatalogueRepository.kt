package id.bachtiar.harits.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.bachtiar.harits.moviecatalogue.data.local.LocalDataSource
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
import id.bachtiar.harits.moviecatalogue.data.remote.ApiResponse
import id.bachtiar.harits.moviecatalogue.data.remote.RemoteDataSource
import id.bachtiar.harits.moviecatalogue.model.*
import id.bachtiar.harits.moviecatalogue.util.AppExecutors
import id.bachtiar.harits.moviecatalogue.util.FilterAndSearchUtils
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class MovieCatalogueRepository @Inject constructor(
    private val remotedDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DataSource {
    override fun getPopularMovies(page: Int, query: String, isFavorite: Boolean): LiveData<DataResult<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, Movies.Response>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(8)
                    .setPageSize(8)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(FilterAndSearchUtils.getMovies(query, isFavorite)), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<Movies.Response>> = remotedDataSource.getPopularMovies(page)

            override fun saveCallResult(data: Movies.Response) {
                val favorites = localDataSource.getFavoriteMovies()
                val movies = data.results?.map {
                    MoviesEntity(
                        movieId = it.id ?: 0,
                        title = it.title.orEmpty(),
                        overview = it.overview.orEmpty(),
                        releaseDate = it.releaseDate.orEmpty(),
                        poster = it.getPosterPath(),
                        isFavourite = favorites.any { favoriteItem -> favoriteItem.movieId == it.id }
                    )
                }
                localDataSource.insertMovies(movies ?: listOf())
            }
        }.asLiveData()
    }

    override suspend fun updateFavoriteMovie(movie: MoviesEntity) {
        movie.isFavourite = !movie.isFavourite
        localDataSource.updateMovies(movie)
    }

    override fun getPopularTvShows(page: Int, query: String, isFavorite: Boolean): LiveData<DataResult<PagedList<TvShowsEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, TvShows.Response>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(8)
                    .setPageSize(8)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(FilterAndSearchUtils.getTvShows(query, isFavorite)), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<TvShows.Response>> = remotedDataSource.getPopularTvShows(page)

            override fun saveCallResult(data: TvShows.Response) {
                val favorites = localDataSource.getFavoriteTvShows()
                val tvShows = data.results?.map {
                    TvShowsEntity(
                        tvShowId = it.id ?: 0,
                        title = it.title.orEmpty(),
                        overview = it.overview.orEmpty(),
                        firstAirDate = it.firstAirDate.orEmpty(),
                        poster = it.getPosterPath(),
                        isFavourite = favorites.any { favoriteItem -> favoriteItem.tvShowId == it.id }
                    )
                }
                localDataSource.insertTvShows(tvShows ?: listOf())
            }
        }.asLiveData()
    }

    override suspend fun updateFavoriteTvShows(tvShow: TvShowsEntity) {
        tvShow.isFavourite = !tvShow.isFavourite
        localDataSource.updateTvShows(tvShow)
    }

    override fun getMovie(id: Int): LiveData<DataResult<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, Movie>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovie(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> = remotedDataSource.getMovie(id)

            override fun saveCallResult(data: Movie) {
                localDataSource.insertMovie(
                    MovieEntity(
                        movieId = id,
                        title = data.title.orEmpty(),
                        overview = data.overview.orEmpty(),
                        releaseDate = data.releaseDate.orEmpty(),
                        poster = data.getPosterPath(),
                        rating = data.rating ?: 0.0,
                        totalUserRating = data.totalUserRating ?: 0,
                        productionCompanies = generateProductionCompanies(data.productionCompanies ?: listOf()),
                        genres = generateCategories(data.genres ?: listOf())
                    )
                )
            }
        }.asLiveData()
    }

    override fun getTvShow(id: Int): LiveData<DataResult<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTvShow(id)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TvShow>> = remotedDataSource.getTvShow(id)

            override fun saveCallResult(data: TvShow) {
                localDataSource.insertTvShow(
                    TvShowEntity(
                        tvShowId = id,
                        title = data.title.orEmpty(),
                        overview = data.overview.orEmpty(),
                        firstAirDate = data.firstAirDate.orEmpty(),
                        poster = data.getPosterPath(),
                        rating = data.rating ?: 0.0,
                        totalUserRating = data.totalUserRating ?: 0,
                        productionCompanies = generateProductionCompanies(data.productionCompanies ?: listOf()),
                        genres = generateCategories(data.genres ?: listOf()),
                        seasons = Json.encodeToString(data.seasons)
                    )
                )
            }
        }.asLiveData()
    }

    private fun generateProductionCompanies(data: List<ProductionCompanies>) : String {
        var result = ""
        data.forEachIndexed { index, productionCompany ->
            result += if (index == (data.size - 1)) {
                productionCompany.name
            } else {
                "${productionCompany.name}, "
            }
        }
        return result
    }

    private fun generateCategories(data: List<Genres>) : String {
        var result = ""
        data.forEachIndexed{index, genres ->
            result += if (index == (data.size - 1)) {
                genres.name
            } else {
                "${genres.name},"
            }
        }
        return result
    }
}