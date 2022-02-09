package id.bachtiar.harits.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
import id.bachtiar.harits.moviecatalogue.databinding.ItemTvShowBinding

class TvShowAdapter : PagedListAdapter<TvShowsEntity, TvShowViewHolder>(TV_SHOWS_COMPARATOR) {

    private lateinit var listener: OnTvShowClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onTvShowClickCallback = listener)
        holder.itemView.setOnClickListener { listener.onItemClicked(item) }
    }


    fun setOnMovieClickCallback(onTvShowClickCallback: OnTvShowClickCallback) {
        this.listener = onTvShowClickCallback
    }

    companion object {
        val TV_SHOWS_COMPARATOR = object: DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean = oldItem.tvShowId == newItem.tvShowId
        }
    }
}