package id.bachtiar.harits.moviecatalogue.ui.list

import id.bachtiar.harits.moviecatalogue.model.Movie

interface OnMovieClickCallback {
    fun onItemClicked(data: Movie)
}