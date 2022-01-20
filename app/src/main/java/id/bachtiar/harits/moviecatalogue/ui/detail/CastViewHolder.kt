package id.bachtiar.harits.moviecatalogue.ui.detail

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import id.bachtiar.harits.moviecatalogue.databinding.ItemCastBinding
import id.bachtiar.harits.moviecatalogue.model.Cast
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

class CastViewHolder constructor(private val viewBinding: ItemCastBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(cast: Cast) {
        viewBinding.apply {
            ivAvatar.load(cast.avatar) {
                transformations(RoundedCornersTransformation(topLeft = ViewUtil.dpToPx(8).toFloat(), topRight = ViewUtil.dpToPx(8).toFloat()))
            }
            tvName.text = cast.name
            tvRole.text = cast.roleName
        }
    }
}