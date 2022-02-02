package id.bachtiar.harits.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.bachtiar.harits.moviecatalogue.databinding.ItemMovieBinding
import id.bachtiar.harits.moviecatalogue.model.Movies

class MovieAdapter constructor(private val items: List<Movies.Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listener: OnMovieClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener { listener.onItemClicked(items[position]) }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun setOnMovieClickCallback(onMovieClickCallback: OnMovieClickCallback) {
        this.listener = onMovieClickCallback
    }
}