package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.bachtiar.harits.moviecatalogue.databinding.ItemSeasonsBinding
import id.bachtiar.harits.moviecatalogue.model.Seasons

class SeasonsAdapter constructor(private val items: List<Seasons>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSeasonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SeasonViewHolder -> holder.bind(items[position])
            else -> Unit
        }
    }

    override fun getItemCount(): Int = items.size
}