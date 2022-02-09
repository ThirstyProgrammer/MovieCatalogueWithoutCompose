package id.bachtiar.harits.moviecatalogue.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey val movieId: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val rating: Double,
    val totalUserRating: Int,
    val productionCompanies: String,
    val genres: String
)