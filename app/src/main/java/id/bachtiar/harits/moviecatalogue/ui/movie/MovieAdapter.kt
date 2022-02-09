package id.bachtiar.harits.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.databinding.ItemMovieBinding

class MovieAdapter : PagedListAdapter<MoviesEntity, MovieViewHolder>(MOVIES_COMPARATOR) {

    private lateinit var listener: OnMovieClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, movieClickCallback = listener)
        holder.itemView.setOnClickListener { listener.onItemClicked(item) }
    }

    fun setOnMovieClickCallback(onMovieClickCallback: OnMovieClickCallback) {
        this.listener = onMovieClickCallback
    }

    companion object {
        val MOVIES_COMPARATOR = object: DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean = oldItem.movieId == newItem.movieId
        }
    }
}