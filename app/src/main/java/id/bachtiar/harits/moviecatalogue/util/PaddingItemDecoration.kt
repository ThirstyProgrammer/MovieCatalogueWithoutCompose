package id.bachtiar.harits.moviecatalogue.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PaddingItemDecoration(private val padding: Int, private val isHorizontal: Boolean = false) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when {
            parent.getChildAdapterPosition(view) == 0 -> with(outRect) {
                if (isHorizontal) {
                    left = padding
                    right = padding / 2
                }else {
                    top = padding
                    bottom = padding / 2
                }
            }
            parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1 -> with(outRect) {
                if (isHorizontal) {
                    left = padding / 2
                    right = padding
                }else{
                    top = padding / 2
                    bottom = padding
                }
            }
            else -> with(outRect) {
                if (isHorizontal) {
                    left = padding / 2
                    right = padding / 2
                }else{
                    top = padding / 2
                    bottom = padding / 2
                }
            }
        }
        if (isHorizontal){
            with(outRect) {
                top = ViewUtil.dpToPx(16)
                bottom = ViewUtil.dpToPx(16)
            }
        }else{
            with(outRect) {
                left = ViewUtil.dpToPx(16)
                right = ViewUtil.dpToPx(16)
            }
        }
    }
}