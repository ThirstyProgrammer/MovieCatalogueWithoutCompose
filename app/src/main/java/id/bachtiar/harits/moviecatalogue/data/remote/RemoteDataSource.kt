package id.bachtiar.harits.moviecatalogue.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.bachtiar.harits.moviecatalogue.BuildConfig
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

    fun getPopularMovies(page: Int): LiveData<ApiResponse<Movies.Response>> {
        val resultMovies = MutableLiveData<ApiResponse<Movies.Response>>()
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getPopularMovies(API_KEY, DEFAULT_LANGUAGE, page)
                    if (result.results?.size != 0) {
                        resultMovies.postValue(ApiResponse.success(result))
                    }else{
                        resultMovies.postValue(ApiResponse.empty("", result))
                    }
                    EspressoIdlingResource.decrement()
                } catch (throwable: Throwable) {
                    handleNetworkError(throwable)
                    resultMovies.postValue(ApiResponse.error(handleNetworkError(throwable), Movies.Response()))
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultMovies
    }

    fun getPopularTvShows(page: Int): LiveData<ApiResponse<TvShows.Response>> {
        val resultTvShows = MutableLiveData<ApiResponse<TvShows.Response>>()
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getPopularTvShows(API_KEY, DEFAULT_LANGUAGE, page)
                    if (result.results?.size != 0) {
                        resultTvShows.postValue(ApiResponse.success(result))
                    }else{
                        resultTvShows.postValue(ApiResponse.empty("", result))
                    }
                    EspressoIdlingResource.decrement()
                } catch (throwable: Throwable) {
                    handleNetworkError(throwable)
                    resultTvShows.postValue(ApiResponse.error(handleNetworkError(throwable), TvShows.Response()))
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultTvShows
    }

    fun getMovie(id: Int): LiveData<ApiResponse<Movie>> {
        val resultMovie = MutableLiveData<ApiResponse<Movie>>()
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getMovie(id, API_KEY, DEFAULT_LANGUAGE)
                    resultMovie.postValue(ApiResponse.success(result))
                    EspressoIdlingResource.decrement()
                } catch (throwable: Throwable) {
                    handleNetworkError(throwable)
                    resultMovie.postValue(ApiResponse.error(handleNetworkError(throwable), Movie()))
                    EspressoIdlingResource.decrement()
                }
            }
        }
        return resultMovie
    }

    fun getTvShow(id: Int): LiveData<ApiResponse<TvShow>> {
        val resultTvShow = MutableLiveData<ApiResponse<TvShow>>()
        EspressoIdlingResource.increment()
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getTvShow(id, API_KEY, DEFAULT_LANGUAGE)
                    resultTvShow.postValue(ApiResponse.success(result))
                    EspressoIdlingResource.decrement()
                } catch (throwable: Throwable) {
                    handleNetworkError(throwable)
                    resultTvShow.postValue(ApiResponse.error(handleNetworkError(throwable), TvShow()))
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