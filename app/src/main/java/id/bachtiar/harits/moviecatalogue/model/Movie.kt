package id.bachtiar.harits.moviecatalogue.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Parcelize
data class Movie(
    val title: String? = "",
    val cover: String? = "",
    val description: String? = "",
    val releaseDate: String? = "",
    val rating: Int? = 0,
    val totalUserRating: Int? = 0,
    val category: List<String>? = listOf(),
    val subDesc: List<SubDesc>? = listOf(),
    val cast: List<Cast>? = listOf(),
): Parcelable
