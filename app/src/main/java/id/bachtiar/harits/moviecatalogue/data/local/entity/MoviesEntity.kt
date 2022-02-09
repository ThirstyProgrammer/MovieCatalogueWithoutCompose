package id.bachtiar.harits.moviecatalogue.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesEntity(
    @PrimaryKey val movieId: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    var isFavourite: Boolean
)