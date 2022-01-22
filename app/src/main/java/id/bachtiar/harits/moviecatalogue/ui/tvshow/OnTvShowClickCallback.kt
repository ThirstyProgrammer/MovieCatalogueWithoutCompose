package id.bachtiar.harits.moviecatalogue.ui.tvshow

import id.bachtiar.harits.moviecatalogue.model.TvShow

interface OnTvShowClickCallback {
    fun onItemClicked(data: TvShow)
}