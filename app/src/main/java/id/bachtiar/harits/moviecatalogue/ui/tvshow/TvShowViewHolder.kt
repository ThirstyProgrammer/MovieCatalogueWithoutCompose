package id.bachtiar.harits.moviecatalogue.ui.tvshow

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
import id.bachtiar.harits.moviecatalogue.databinding.ItemTvShowBinding
import id.bachtiar.harits.moviecatalogue.util.StringHelper.getDateForView
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

class TvShowViewHolder constructor(private val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(tvShow: TvShowsEntity?, onTvShowClickCallback: OnTvShowClickCallback) {
        binding.apply {
            ivCover.load(tvShow?.poster) {
                transformations(RoundedCornersTransformation(topLeft = ViewUtil.dpToPx(8).toFloat(), bottomLeft = ViewUtil.dpToPx(8).toFloat()))
            }
            tvTitle.text = tvShow?.title
            tvReleaseDate.text = getDateForView(tvShow?.firstAirDate)
            tvDescription.text = tvShow?.overview
            val drawable = if (tvShow?.isFavourite == true) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            btnFavorite.setImageDrawable(root.context.getDrawable(drawable))
            btnFavorite.setOnClickListener {
                onTvShowClickCallback.onFavouriteClicked(tvShow)
            }
        }
    }
}