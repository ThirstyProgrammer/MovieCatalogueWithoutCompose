package id.bachtiar.harits.moviecatalogue.util

import id.bachtiar.harits.moviecatalogue.databinding.ViewStateBinding
import android.view.View
import id.bachtiar.harits.moviecatalogue.data.ViewState

fun ViewStateBinding.handleViewState(state: ViewState, message: String = "") {
    when (state) {
        ViewState.LOADING -> {
            containerViewState.visibility = View.VISIBLE
            containerError.visibility = View.GONE
            containerLoading.visibility = View.VISIBLE
        }
        ViewState.SUCCESS -> {
            containerViewState.visibility = View.GONE
            containerError.visibility = View.GONE
            containerLoading.visibility = View.GONE
        }
        ViewState.ERROR -> {
            containerViewState.visibility = View.VISIBLE
            containerError.visibility = View.VISIBLE
            containerLoading.visibility = View.GONE
            tvErrorMessage.text = message
        }
    }
}