package id.bachtiar.harits.moviecatalogue.ui.movie

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.bachtiar.harits.moviecatalogue.databinding.ItemMovieBinding
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

class MovieViewHolder constructor(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.apply {
            ivCover.load(movie.cover) {
                transformations(RoundedCornersTransformation(topLeft = ViewUtil.dpToPx(8).toFloat(), bottomLeft = ViewUtil.dpToPx(8).toFloat()))
            }
            tvTitle.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvDescription.text = movie.description
        }
    }
}