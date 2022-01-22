package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.bachtiar.harits.moviecatalogue.databinding.ItemTvShowBinding
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

class TvShowViewHolder constructor(private val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: TvShow) {
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