package id.bachtiar.harits.moviecatalogue.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity

@Database(
    entities = [MoviesEntity::class, TvShowsEntity::class, MovieEntity::class, TvShowEntity::class],
    version = 1
)
abstract class MovieCatalogDatabase : RoomDatabase() {

    abstract fun movieCatalogueDao(): MovieCatalogueDao
}