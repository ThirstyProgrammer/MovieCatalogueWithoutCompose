package id.bachtiar.harits.moviecatalogue.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.bachtiar.harits.moviecatalogue.data.local.LocalDataSource
import id.bachtiar.harits.moviecatalogue.data.local.room.MovieCatalogDatabase
import id.bachtiar.harits.moviecatalogue.data.local.room.MovieCatalogueDao
import id.bachtiar.harits.moviecatalogue.util.AppExecutors
import id.bachtiar.harits.moviecatalogue.util.Constant
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideMovieCatalogueDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MovieCatalogDatabase::class.java, Constant.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideMovieCatalogueDao(database: MovieCatalogDatabase) = database.movieCatalogueDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(movieCatalogueDao: MovieCatalogueDao) = LocalDataSource(movieCatalogueDao)

    @Provides
    @Singleton
    fun provideAppExecutors() = AppExecutors()
}