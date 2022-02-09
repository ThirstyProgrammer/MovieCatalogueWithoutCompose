package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.size.Scale
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowEntity
import id.bachtiar.harits.moviecatalogue.databinding.FragmentDetailTvShowBinding
import id.bachtiar.harits.moviecatalogue.ui.MainActivity
import id.bachtiar.harits.moviecatalogue.util.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class DetailTvShowFragment : Fragment(R.layout.fragment_detail_tv_show) {

    private lateinit var seasonsAdapter: SeasonsAdapter
    private val args: DetailTvShowFragmentArgs by navArgs()
    private val binding: FragmentDetailTvShowBinding by viewBinding(FragmentDetailTvShowBinding::bind, R.id.container_detail)
    private val mTvShowViewModel: DetailTvShowViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideMenu()
        handleViewModelObserver()
    }

    private fun handleViewModelObserver() {
        mTvShowViewModel.getTvShow(args.id).observe(viewLifecycleOwner) {
            binding.viewState.handleViewState(it.status, it.message.orEmpty())
            if (it.data != null) {
                setupView(it.data)
            }
        }
    }

    private fun setupView(data: TvShowEntity) {
        requireActivity().title = data.title
        binding.apply {
            val progress = data.rating
            ivCover.load(data.poster) {
                scale(Scale.FILL)
            }
            tvProgress.text = progress.toString()
            circularProgressBar.progress = progress.toFloat()
            tvTotalUserRating.text = getRating(data.totalUserRating)
            tvReleaseDate.text = StringHelper.getDateForView(data.firstAirDate)
            tvSubDesc.text = getSubDesc(data.productionCompanies)
            if (data.seasons.isNotBlank()){
                seasonsAdapter = SeasonsAdapter(Json.decodeFromString(data.seasons) ?: listOf())
                rvSeasons.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = seasonsAdapter
                    if (itemDecorationCount == 0) {
                        addItemDecoration(PaddingItemDecoration(ViewUtil.dpToPx(16), true))
                    }
                }
            }
            tvSeasons.isVisible = data.seasons.isNotBlank()
            rvSeasons.isVisible = data.seasons.isNotBlank()
            tvDescription.text = data.overview
            if (data.genres.isNotBlank()) {
                val genres = data.genres.split(",")
                genres.forEach {
                    val chip = Chip(requireContext())
                    chip.text = it
                    chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_secondary_dark))
                    chip.chipBackgroundColor = ContextCompat.getColorStateList(requireContext(), R.color.color_secondary_light)
                    chip.isClickable = false
                    cgCategory.addView(chip)
                }
            }
            tvCategoryTitle.isVisible = data.genres.isNotBlank()
            cgCategory.isVisible = data.genres.isNotBlank()
        }
    }

    private fun getRating(rating: Int): String {
        return "${rating.formatWithThousandComma()} Ratings"
    }

    private fun getSubDesc(data: String): SpannableStringBuilder {
        val generatedSubDesc = SpannableStringBuilder()
        generatedSubDesc.bold { appendLine("Production Company :") }
        generatedSubDesc.append(data)
        return generatedSubDesc
    }
}