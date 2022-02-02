package id.bachtiar.harits.moviecatalogue.model

import androidx.annotation.Keep
import id.bachtiar.harits.moviecatalogue.util.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Movie(
    @SerialName("original_title") val title: String? = "",
    @SerialName("poster_path") val poster: String? = "",
    @SerialName("release_date") val releaseDate: String? = "",
    @SerialName("vote_average") val rating: Double? = 0.0,
    @SerialName("vote_count") val totalUserRating: Int? = 0,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanies>? = listOf(),
    val genres: List<Genres>? = listOf(),
    val overview: String? = "",
) {
    fun getPosterPath(): String = "${Constant.POSTER_PREFIX_PATH}$poster"
}
