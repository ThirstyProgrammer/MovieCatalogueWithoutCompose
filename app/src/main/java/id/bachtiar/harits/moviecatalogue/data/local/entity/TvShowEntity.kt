package id.bachtiar.harits.moviecatalogue.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowEntity(
    @PrimaryKey val tvShowId: Int,
    val title: String,
    val overview: String,
    val firstAirDate: String,
    val poster: String,
    val rating: Double,
    val totalUserRating: Int,
    val productionCompanies: String,
    val genres: String,
    val seasons: String
)