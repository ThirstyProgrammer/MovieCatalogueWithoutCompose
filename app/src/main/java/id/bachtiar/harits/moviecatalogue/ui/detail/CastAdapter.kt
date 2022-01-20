package id.bachtiar.harits.moviecatalogue.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.bachtiar.harits.moviecatalogue.databinding.ItemCastBinding
import id.bachtiar.harits.moviecatalogue.model.Cast

class CastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items : ArrayList<Cast> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CastViewHolder -> holder.bind(items[position])
            else -> Unit
        }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Cast>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}