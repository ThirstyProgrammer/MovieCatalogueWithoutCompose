package id.bachtiar.harits.moviecatalogue.ui.tvshow

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.bachtiar.harits.moviecatalogue.databinding.ItemTvShowBinding
import id.bachtiar.harits.moviecatalogue.model.TvShows
import id.bachtiar.harits.moviecatalogue.util.StringHelper.getDateForView
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

class TvShowViewHolder constructor(private val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: TvShows.Data) {
        binding.apply {
            ivCover.load(movie.getPosterPath()) {
                transformations(RoundedCornersTransformation(topLeft = ViewUtil.dpToPx(8).toFloat(), bottomLeft = ViewUtil.dpToPx(8).toFloat()))
            }
            tvTitle.text = movie.title
            tvReleaseDate.text = getDateForView(movie.firstAirDate)
            tvDescription.text = movie.overview
        }
    }
}