package id.bachtiar.harits.moviecatalogue.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.remote.RemoteDataSource
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@Module(
    includes = [NetworkModule::class]
)
@InstallIn(SingletonComponent::class)
class AppModule {

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRepository2(
        remoteDataSource: RemoteDataSource
    ) = MovieCatalogueRepository(remoteDataSource)
}