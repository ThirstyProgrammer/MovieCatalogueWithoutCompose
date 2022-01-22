package id.bachtiar.harits.moviecatalogue.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Data(
    val movies: List<Movie>? = listOf(),
    val tvShows: List<TvShow>? = listOf(),
)