package id.bachtiar.harits.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import id.bachtiar.harits.moviecatalogue.data.local.LocalDataSource
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
import id.bachtiar.harits.moviecatalogue.data.remote.RemoteDataSource
import id.bachtiar.harits.moviecatalogue.util.AppExecutors
import id.bachtiar.harits.moviecatalogue.util.DataDummy
import id.bachtiar.harits.moviecatalogue.utils.PagedListUtil
import id.bachtiar.harits.moviecatalogue.utils.getOrAwaitValueTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote: RemoteDataSource = mock()
    private val local: LocalDataSource = mock()
    private val appExecutors: AppExecutors = mock()
    private val fakeRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val dummyResponseMovies = DataDummy.getMovies()
    private val dummyResponseFavoriteMovies = DataDummy.getFavoriteMovies()
    private val dummyResponseMovie = DataDummy.getMovie()
    private val dummyResponseTvShows = DataDummy.getTvShows()
    private val dummyResponseFavoriteTvShows = DataDummy.getFavoriteTvShows()
    private val dummyResponseTvShow = DataDummy.getTvShow()


    @Test
    fun getPopularMovies() {
        val query = "QUERY"
        val dataSourceFactory: DataSource.Factory<Int, MoviesEntity> = mock()
        `when`(local.getMovies(query)).thenReturn(dataSourceFactory)
        fakeRepository.getPopularMovies(1, query)
        val moviesEntities = DataResult.success(PagedListUtil.mockPagedList(DataDummy.getMovies()))
        verify(local).getMovies(query)
        assertNotNull(moviesEntities.data)
        assertEquals(dummyResponseMovies.size, moviesEntities.data?.size)
        assertEquals(dummyResponseMovies.first(), moviesEntities.data?.first())
        assertEquals(dummyResponseMovies.last(), moviesEntities.data?.last())
    }

    @Test
    fun getFavoriteMoviesWithQuery() {
        val query = "QUERY"
        val dataSourceFactory: DataSource.Factory<Int, MoviesEntity> = mock()
        `when`(local.getFavoriteMoviesWithQuery(query)).thenReturn(dataSourceFactory)
        fakeRepository.getFavoriteMoviesWithQuery(query)
        val listMoviesEntity = PagedListUtil.mockPagedList(DataDummy.getFavoriteMovies())
        verify(local).getFavoriteMoviesWithQuery(query)
        assertNotNull(listMoviesEntity)
        assertEquals(dummyResponseFavoriteMovies.size, listMoviesEntity.size)
        assertEquals(dummyResponseFavoriteMovies.first(), listMoviesEntity.first())
        assertTrue(listMoviesEntity.first().isFavourite)
        assertEquals(dummyResponseFavoriteMovies.last(), listMoviesEntity.last())
        assertTrue(listMoviesEntity.last().isFavourite)
    }

    @Test
    fun updateFavoriteMovie() {
        fakeRepository.updateFavoriteMovie(DataDummy.generateSelectedMovies())
        verify(local).updateMovies(DataDummy.generateSelectedUpdatedMovies())
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getPopularTvShows() {
        val query = "QUERY"
        val dataSourceFactory: DataSource.Factory<Int, TvShowsEntity> = mock()
        `when`(local.getTvShows(query)).thenReturn(dataSourceFactory)
        fakeRepository.getPopularTvShows(1, query)
        val moviesEntities = DataResult.success(PagedListUtil.mockPagedList(DataDummy.getTvShows()))
        verify(local).getTvShows(query)
        assertNotNull(moviesEntities.data)
        assertEquals(dummyResponseTvShows.size, moviesEntities.data?.size)
        assertEquals(dummyResponseTvShows.first(), moviesEntities.data?.first())
        assertEquals(dummyResponseTvShows.last(), moviesEntities.data?.last())
    }

    @Test
    fun getFavoriteTvShowsWithQuery() {
        val query = "QUERY"
        val dataSourceFactory: DataSource.Factory<Int, TvShowsEntity> = mock()
        `when`(local.getFavoriteTvShowsWithQuery(query)).thenReturn(dataSourceFactory)
        fakeRepository.getFavoriteTvShowsWithQuery(query)
        val listMoviesEntity = PagedListUtil.mockPagedList(DataDummy.getFavoriteTvShows())
        verify(local).getFavoriteTvShowsWithQuery(query)
        assertNotNull(listMoviesEntity)
        assertEquals(dummyResponseFavoriteTvShows.size, listMoviesEntity.size)
        assertEquals(dummyResponseFavoriteTvShows.first(), listMoviesEntity.first())
        assertTrue(listMoviesEntity.first().isFavourite)
        assertEquals(dummyResponseFavoriteTvShows.last(), listMoviesEntity.last())
        assertTrue(listMoviesEntity.last().isFavourite)
    }

    @Test
    fun updateFavoriteTvShows() {
        fakeRepository.updateFavoriteTvShows(DataDummy.generateSelectedTvShows())
        verify(local).updateTvShows(DataDummy.generateSelectedUpdatedTvShows())
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getMovie() {
        val dummyDetailMovie = MutableLiveData<MovieEntity>()
        dummyDetailMovie.value = DataDummy.getMovie()
        `when`(local.getMovie(1)).thenReturn(dummyDetailMovie)

        val movieEntity = fakeRepository.getMovie(1).getOrAwaitValueTest()
        verify(local).getMovie(1)
        assertNotNull(movieEntity)
        assertEquals(dummyResponseMovie.movieId, movieEntity.data?.movieId)
        assertEquals(dummyResponseMovie.title, movieEntity.data?.title)
        assertEquals(dummyResponseMovie.overview, movieEntity.data?.overview)
        assertEquals(dummyResponseMovie.releaseDate, movieEntity.data?.releaseDate)
        assertEquals(dummyResponseMovie.poster, movieEntity.data?.poster)
        assertEquals(dummyResponseMovie.rating, movieEntity.data?.rating)
        assertEquals(dummyResponseMovie.totalUserRating, movieEntity.data?.totalUserRating)
        assertEquals(dummyResponseMovie.productionCompanies, movieEntity.data?.productionCompanies)
        assertEquals(dummyResponseMovie.genres, movieEntity.data?.genres)
    }

    @Test
    fun getTvShow() {
        val dummyDetailMovie = MutableLiveData<TvShowEntity>()
        dummyDetailMovie.value = DataDummy.getTvShow()
        `when`(local.getTvShow(1)).thenReturn(dummyDetailMovie)

        val movieEntity = fakeRepository.getTvShow(1).getOrAwaitValueTest()
        verify(local).getTvShow(1)
        assertNotNull(movieEntity)
        assertEquals(dummyResponseTvShow.tvShowId, movieEntity.data?.tvShowId)
        assertEquals(dummyResponseTvShow.title, movieEntity.data?.title)
        assertEquals(dummyResponseTvShow.overview, movieEntity.data?.overview)
        assertEquals(dummyResponseTvShow.firstAirDate, movieEntity.data?.firstAirDate)
        assertEquals(dummyResponseTvShow.poster, movieEntity.data?.poster)
        assertEquals(dummyResponseTvShow.rating, movieEntity.data?.rating)
        assertEquals(dummyResponseTvShow.totalUserRating, movieEntity.data?.totalUserRating)
        assertEquals(dummyResponseTvShow.productionCompanies, movieEntity.data?.productionCompanies)
        assertEquals(dummyResponseTvShow.genres, movieEntity.data?.genres)
        assertEquals(dummyResponseTvShow.seasons, movieEntity.data?.seasons)
    }
}