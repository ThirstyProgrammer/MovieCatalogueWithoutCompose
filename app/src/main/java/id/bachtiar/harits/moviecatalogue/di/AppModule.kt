package id.bachtiar.harits.moviecatalogue.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.LocalDataSource
import id.bachtiar.harits.moviecatalogue.data.remote.RemoteDataSource
import id.bachtiar.harits.moviecatalogue.util.AppExecutors
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@Module(
    includes = [NetworkModule::class, CacheModule::class]
)
@InstallIn(SingletonComponent::class)
class AppModule {

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        appExecutors: AppExecutors
    ) = MovieCatalogueRepository(remoteDataSource, localDataSource, appExecutors)
}