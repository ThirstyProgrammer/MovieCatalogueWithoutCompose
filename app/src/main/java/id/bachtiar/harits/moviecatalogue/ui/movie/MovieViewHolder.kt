package id.bachtiar.harits.moviecatalogue.ui.movie

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.databinding.ItemMovieBinding
import id.bachtiar.harits.moviecatalogue.util.StringHelper.getDateForView
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

class MovieViewHolder constructor(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(movie: MoviesEntity?, movieClickCallback: OnMovieClickCallback) {
        binding.apply {
            ivCover.load(movie?.poster) {
                transformations(RoundedCornersTransformation(topLeft = ViewUtil.dpToPx(8).toFloat(), bottomLeft = ViewUtil.dpToPx(8).toFloat()))
            }
            tvTitle.text = movie?.title
            tvReleaseDate.text = getDateForView(movie?.releaseDate)
            tvDescription.text = movie?.overview
            val drawable = if (movie?.isFavourite == true) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            btnFavorite.setImageDrawable(root.context.getDrawable(drawable))
            btnFavorite.setOnClickListener {
                movieClickCallback.onFavouriteClicked(movie)
            }
        }
    }
}