package id.bachtiar.harits.moviecatalogue.ui.tvshow

import id.bachtiar.harits.moviecatalogue.model.TvShows

interface OnTvShowClickCallback {
    fun onItemClicked(data: TvShows.Data)
}