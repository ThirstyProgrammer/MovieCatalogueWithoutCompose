package id.bachtiar.harits.moviecatalogue.model

import androidx.annotation.Keep
import id.bachtiar.harits.moviecatalogue.util.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Seasons(
    val name: String? = "",
    @SerialName("poster_path") val poster: String? = "",
    @SerialName("air_date") val airDate: String? = "",
    @SerialName("episode_count") val episodeCount: Int? = 0,
    @SerialName("season_number") val seasonNumber: Int? = 0,
){
    fun getPosterPath(): String = "${Constant.POSTER_PREFIX_PATH}$poster"
}
