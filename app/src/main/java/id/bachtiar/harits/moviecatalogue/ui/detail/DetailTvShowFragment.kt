package id.bachtiar.harits.moviecatalogue.ui.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.size.Scale
import com.google.android.material.chip.Chip
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.FragmentDetailBinding
import id.bachtiar.harits.moviecatalogue.model.SubDesc
import id.bachtiar.harits.moviecatalogue.util.PaddingItemDecoration
import id.bachtiar.harits.moviecatalogue.util.ViewUtil
import id.bachtiar.harits.moviecatalogue.util.formatWithThousandComma

class DetailTvShowFragment : Fragment(R.layout.fragment_detail) {

    private val args: DetailTvShowFragmentArgs by navArgs()
    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind, R.id.container_detail)
    private val mTvShowViewModel: DetailTvShowViewModel by viewModels()
    private val castAdapter = CastAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTvShowViewModel.tvShow = args.tvShow
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = mTvShowViewModel.tvShow.title
        binding.apply {
            val progress = mTvShowViewModel.tvShow.rating ?: 0
            ivCover.load(mTvShowViewModel.tvShow.cover) {
                scale(Scale.FILL)
            }
            tvProgress.text = progress.toString()
            circularProgressBar.progress = progress.toFloat()
            tvTotalUserRating.text = getRating(mTvShowViewModel.tvShow.totalUserRating ?: 0)
            tvReleaseDate.text = mTvShowViewModel.tvShow.releaseDate
            tvSubDesc.text = getSubDesc(mTvShowViewModel.tvShow.subDesc ?: listOf())
            rvCast.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = castAdapter
                if (itemDecorationCount == 0) {
                    addItemDecoration(PaddingItemDecoration(ViewUtil.dpToPx(16), true))
                }
            }
            castAdapter.setData(mTvShowViewModel.tvShow.cast ?: listOf())
            tvDescription.text = mTvShowViewModel.tvShow.description
            mTvShowViewModel.tvShow.category?.forEach {
                val chip = Chip(requireContext())
                chip.text = it
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_secondary_dark))
                chip.chipBackgroundColor = ContextCompat.getColorStateList(requireContext(), R.color.color_secondary_light)
                chip.isClickable = false
                cgCategory.addView(chip)
            }
        }
    }

    private fun getRating(rating: Int) : String {
        return "${rating.formatWithThousandComma()} Ratings"
    }

    private fun getSubDesc(data: List<SubDesc>): SpannableStringBuilder {
        val generatedSubDesc = SpannableStringBuilder()
        val size = data.size
        data.forEachIndexed { index, subDesc ->
            if (index == (size - 1)){
                generatedSubDesc.bold { appendLine("${subDesc.title} :") }.append("${subDesc.description}")
            }else{
                generatedSubDesc.bold { appendLine("${subDesc.title} :") }.appendLine("${subDesc.description}\n")
            }
        }
        return generatedSubDesc
    }
}