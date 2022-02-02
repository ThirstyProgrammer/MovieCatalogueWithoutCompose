package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.ItemSeasonsBinding
import id.bachtiar.harits.moviecatalogue.model.Seasons
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

class SeasonViewHolder constructor(private val viewBinding: ItemSeasonsBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(seasons: Seasons) {
        viewBinding.apply {
            ivAvatar.load(seasons.getPosterPath()) {
                transformations(RoundedCornersTransformation(topLeft = ViewUtil.dpToPx(8).toFloat(), topRight = ViewUtil.dpToPx(8).toFloat()))
            }
            tvName.text = seasons.name
            tvEpisode.text = viewBinding.root.context.getString(R.string.total_episode, seasons.episodeCount)
        }
    }
}