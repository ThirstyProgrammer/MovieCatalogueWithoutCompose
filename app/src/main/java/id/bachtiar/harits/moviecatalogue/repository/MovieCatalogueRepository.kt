package id.bachtiar.harits.moviecatalogue.repository

import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.model.TvShows
import id.bachtiar.harits.moviecatalogue.network.ApiService
import javax.inject.Inject

class MovieCatalogueRepository @Inject constructor(private val service: ApiService) {

    companion object {
        const val DEFAULT_LANGUAGE = "en-US"
        const val API_KEY = "3cfebc632cc9b161eef6e6f23409809a"
    }

    suspend fun getPopularMovies(page: Int = 1): Movies.Response {
        return service.getPopularMovies(API_KEY, DEFAULT_LANGUAGE, page)
    }

    suspend fun getMovie(id: Int): Movie {
        return service.getMovie(id, API_KEY, DEFAULT_LANGUAGE)
    }

    suspend fun getPopularTvShows(page: Int = 1): TvShows.Response {
        return service.getPopularTvShows(API_KEY, DEFAULT_LANGUAGE, page)
    }

    suspend fun getTvShow(id: Int): TvShow {
        return service.getTvShow(id, API_KEY, DEFAULT_LANGUAGE)
    }
}