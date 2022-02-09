package id.bachtiar.harits.moviecatalogue.ui.movie

import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity

interface OnMovieClickCallback {
    fun onItemClicked(data: MoviesEntity?)
    fun onFavouriteClicked(data: MoviesEntity?)
}