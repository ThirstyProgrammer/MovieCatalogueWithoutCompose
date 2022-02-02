package id.bachtiar.harits.moviecatalogue.ui.movie

import id.bachtiar.harits.moviecatalogue.model.Movies

interface OnMovieClickCallback {
    fun onItemClicked(data: Movies.Data)
}