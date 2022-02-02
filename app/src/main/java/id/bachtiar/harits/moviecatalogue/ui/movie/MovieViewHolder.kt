package id.bachtiar.harits.moviecatalogue.ui.movie

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.bachtiar.harits.moviecatalogue.databinding.ItemMovieBinding
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.util.StringHelper.getDateForView
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

class MovieViewHolder constructor(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movies.Data) {
        binding.apply {
            ivCover.load(movie.getPosterPath()) {
                transformations(RoundedCornersTransformation(topLeft = ViewUtil.dpToPx(8).toFloat(), bottomLeft = ViewUtil.dpToPx(8).toFloat()))
            }
            tvTitle.text = movie.title
            tvReleaseDate.text = getDateForView(movie.releaseDate)
            tvDescription.text = movie.overview
        }
    }
}