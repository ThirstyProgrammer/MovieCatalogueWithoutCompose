package id.bachtiar.harits.moviecatalogue.ui.tvshow

import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity

interface OnTvShowClickCallback {
    fun onItemClicked(data: TvShowsEntity?)
    fun onFavouriteClicked(data: TvShowsEntity?)
}