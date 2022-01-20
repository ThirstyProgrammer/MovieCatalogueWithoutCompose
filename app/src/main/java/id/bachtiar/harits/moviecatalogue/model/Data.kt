package id.bachtiar.harits.moviecatalogue.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Data(
    val categories: List<Category>? = listOf(),
)