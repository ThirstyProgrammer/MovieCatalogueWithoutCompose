package id.bachtiar.harits.moviecatalogue.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Genres(
    val id: Int? = 0,
    val name: String? = ""
)
