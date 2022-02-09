package id.bachtiar.harits.moviecatalogue.util

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object FilterAndSearchUtils {

    fun getMovies(query: String, isFavorite: Boolean) : SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM MoviesEntity ")
        when {
            query.isNotBlank() && isFavorite -> {
                simpleQuery.append("WHERE isFavourite AND title LIKE '%$query%'")
            }
            query.isNotBlank() && !isFavorite -> {
                simpleQuery.append("WHERE title LIKE '%$query%' ORDER BY isFavourite DESC")
            }
            isFavorite -> {
                simpleQuery.append("WHERE isFavourite")
            }
            !isFavorite -> {
                simpleQuery.append("ORDER BY isFavourite DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getTvShows(query: String, isFavorite: Boolean) : SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM TvShowsEntity ")
        when {
            query.isNotBlank() && isFavorite -> {
                simpleQuery.append("WHERE isFavourite AND title LIKE '%$query%'")
            }
            query.isNotBlank() && !isFavorite -> {
                simpleQuery.append("WHERE title LIKE '%$query%' ORDER BY isFavourite DESC")
            }
            isFavorite -> {
                simpleQuery.append("WHERE isFavourite")
            }
            !isFavorite -> {
                simpleQuery.append("ORDER BY isFavourite DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}