package id.bachtiar.harits.moviecatalogue.util

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object FilterAndSearchUtils {

    fun getMovies(query: String) : SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM MoviesEntity ")
        when {
            query.isNotBlank() -> {
                simpleQuery.append("WHERE title LIKE '%$query%' ORDER BY isFavourite DESC")
            }
            else -> {
                simpleQuery.append("ORDER BY isFavourite DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getFavoriteMovies(query: String) : SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM MoviesEntity ")
        when {
            query.isNotBlank()-> {
                simpleQuery.append("WHERE isFavourite AND title LIKE '%$query%'")
            }
            else -> {
                simpleQuery.append("WHERE isFavourite")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getTvShows(query: String) : SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM TvShowsEntity ")
        when {
            query.isNotBlank() -> {
                simpleQuery.append("WHERE title LIKE '%$query%' ORDER BY isFavourite DESC")
            }
            else -> {
                simpleQuery.append("ORDER BY isFavourite DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getFavoriteTvShows(query: String) : SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM TvShowsEntity ")
        when {
            query.isNotBlank() -> {
                simpleQuery.append("WHERE isFavourite AND title LIKE '%$query%'")
            }
            else -> {
                simpleQuery.append("WHERE isFavourite")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}