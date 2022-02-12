package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.data.DataResult
import id.bachtiar.harits.moviecatalogue.data.MovieCatalogueRepository
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repo: MovieCatalogueRepository
) : ViewModel() {

    fun getPopularTvShows(query: String): LiveData<DataResult<PagedList<TvShowsEntity>>> = repo.getPopularTvShows(query = query)

    fun getFavoriteTvShows(query: String): LiveData<PagedList<TvShowsEntity>> = repo.getFavoriteTvShowsWithQuery(query)

    fun updateFavorite(tvShow: TvShowsEntity) {
        repo.updateFavoriteTvShows(tvShow)
    }
}