package id.bachtiar.harits.moviecatalogue.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.bachtiar.harits.moviecatalogue.BuildConfig
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.model.TvShows
import id.bachtiar.harits.moviecatalogue.network.ApiService
import id.bachtiar.harits.moviecatalogue.util.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getPopularMovies(page: Int): LiveData<DataResult<Movies.Response>> {
        val resultMovies = MutableLiveData<DataResult<Movies.Response>>()
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            resultMovies.postValue(DataResult.loading(null))
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getPopularMovies(API_KEY, DEFAULT_LANGUAGE, page)
                    resultMovies.postValue(DataResult.success(result))
                    EspressoIdlingResource.decrement()
                } catch (throwable: Throwable) {
                    handleNetworkError(throwable)
                    resultMovies.postValue(DataResult.error(handleNetworkError(throwable), null))
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultMovies
    }

    fun getPopularTvShows(page: Int): LiveData<DataResult<TvShows.Response>> {
        val resultTvShows = MutableLiveData<DataResult<TvShows.Response>>()
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            resultTvShows.postValue(DataResult.loading(null))
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getPopularTvShows(API_KEY, DEFAULT_LANGUAGE, page)
                    resultTvShows.postValue(DataResult.success(result))
                    EspressoIdlingResource.decrement()
                } catch (throwable: Throwable) {
                    handleNetworkError(throwable)
                    resultTvShows.postValue(DataResult.error(handleNetworkError(throwable), null))
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultTvShows
    }

    fun getMovie(id: Int): LiveData<DataResult<Movie>> {
        val resultMovie = MutableLiveData<DataResult<Movie>>()
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            resultMovie.postValue(DataResult.loading(null))
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getMovie(id, API_KEY, DEFAULT_LANGUAGE)
                    resultMovie.postValue(DataResult.success(result))
                    EspressoIdlingResource.decrement()
                } catch (throwable: Throwable) {
                    handleNetworkError(throwable)
                    resultMovie.postValue(DataResult.error(handleNetworkError(throwable), null))
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultMovie
    }
    fun getTvShow(id: Int): LiveData<DataResult<TvShow>> {
        val resultTvShow = MutableLiveData<DataResult<TvShow>>()
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            resultTvShow.postValue(DataResult.loading(null))
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getTvShow(id, API_KEY, DEFAULT_LANGUAGE)
                    resultTvShow.postValue(DataResult.success(result))
                    EspressoIdlingResource.decrement()
                } catch (throwable: Throwable) {
                    handleNetworkError(throwable)
                    resultTvShow.postValue(DataResult.error(handleNetworkError(throwable), null))
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultTvShow
    }

    private fun handleNetworkError(throwable: Throwable): String {
        val message: String = when (throwable) {
            is IOException -> {
                "Network Error"
            }
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = throwable.message()
                "Error $code $errorResponse"
            }
            else -> {
                "Unknown Error"
            }
        }
        return message
    }

    companion object {
        const val DEFAULT_LANGUAGE = "en-US"
        const val API_KEY = BuildConfig.API_KEY
    }
}