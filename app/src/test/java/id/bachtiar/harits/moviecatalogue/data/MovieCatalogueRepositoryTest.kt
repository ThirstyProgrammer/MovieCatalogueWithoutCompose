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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    private val dummyResponseMovie = DataDummy.getMovie()
    private val dummyResponseTvShows = DataDummy.getTvShows()
    private val dummyResponseTvShow = DataDummy.getTvShow()


    @Test
    fun getPopularMovies() {
        val queryAndFavorite = Pair("", false)
        val dataSourceFactory: DataSource.Factory<Int, MoviesEntity> = mock()
        `when`(local.getMovies(queryAndFavorite)).thenReturn(dataSourceFactory)
        fakeRepository.getPopularMovies(1, queryAndFavorite)
        val moviesEntities = DataResult.success(PagedListUtil.mockPagedList(DataDummy.getMovies()))
        verify(local).getMovies(queryAndFavorite)
        assertNotNull(moviesEntities.data)
        assertEquals(dummyResponseMovies.size, moviesEntities.data?.size)
        assertEquals(dummyResponseMovies.first(), moviesEntities.data?.first())
        assertEquals(dummyResponseMovies.last(), moviesEntities.data?.last())
    }

    @Test
    fun updateFavoriteMovie(){
        fakeRepository.updateFavoriteMovie(DataDummy.generateSelectedMovies())
        verify(local).updateMovies(DataDummy.generateSelectedUpdatedMovies())
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getPopularTvShows() {
        val queryAndFavorite = Pair("", false)
        val dataSourceFactory: DataSource.Factory<Int, TvShowsEntity> = mock()
        `when`(local.getTvShows(queryAndFavorite)).thenReturn(dataSourceFactory)
        fakeRepository.getPopularTvShows(1, queryAndFavorite)
        val moviesEntities = DataResult.success(PagedListUtil.mockPagedList(DataDummy.getTvShows()))
        verify(local).getTvShows(queryAndFavorite)
        assertNotNull(moviesEntities.data)
        assertEquals(dummyResponseTvShows.size, moviesEntities.data?.size)
        assertEquals(dummyResponseTvShows.first(), moviesEntities.data?.first())
        assertEquals(dummyResponseTvShows.last(), moviesEntities.data?.last())
    }

    @Test
    fun updateFavoriteTvShows(){
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