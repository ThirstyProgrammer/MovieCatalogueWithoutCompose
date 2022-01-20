package id.bachtiar.harits.moviecatalogue.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Parcelize
data class Cast(
    val name: String? = "",
    val roleName: String? = "",
    val avatar: String? = "",
): Parcelable
