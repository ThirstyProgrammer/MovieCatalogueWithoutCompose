package id.bachtiar.harits.moviecatalogue.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.bachtiar.harits.moviecatalogue.databinding.ItemTvShowBinding
import id.bachtiar.harits.moviecatalogue.model.TvShows

class TvShowAdapter constructor(private val items: List<TvShows.Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listener: OnTvShowClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TvShowViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener { listener.onItemClicked(items[position]) }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun setOnMovieClickCallback(onTvShowClickCallback: OnTvShowClickCallback) {
        this.listener = onTvShowClickCallback
    }
}