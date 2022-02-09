package id.bachtiar.harits.moviecatalogue.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowsEntity(
    @PrimaryKey val tvShowId: Int,
    val title: String,
    val overview: String,
    val firstAirDate: String,
    val poster: String,
    var isFavourite: Boolean
)