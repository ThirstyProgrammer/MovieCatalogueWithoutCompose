package id.bachtiar.harits.moviecatalogue.model

import androidx.annotation.Keep
import id.bachtiar.harits.moviecatalogue.util.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class Movies {

    @Keep
    @Serializable
    data class Response(
        val page: Int? = 0,
        @SerialName("total_pages") val totalPages: Int? = 0,
        val results: List<Data>? = listOf()
    )

    @Keep
    @Serializable
    data class Data(
        val id: Int? = 0,
        @SerialName("original_title") val title: String? = "",
        val overview: String? = "",
        @SerialName("release_date") val releaseDate: String? = "",
        @SerialName("poster_path") val poster: String? = ""
    ) {

        fun getPosterPath(): String = "${Constant.POSTER_PREFIX_PATH}$poster"
    }
}