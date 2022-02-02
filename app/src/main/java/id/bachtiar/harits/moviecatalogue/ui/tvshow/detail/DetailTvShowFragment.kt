package id.bachtiar.harits.moviecatalogue.ui.tvshow.detail

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
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.FragmentDetailTvShowBinding
import id.bachtiar.harits.moviecatalogue.model.ProductionCompanies
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.util.*

@AndroidEntryPoint
class DetailTvShowFragment : Fragment(R.layout.fragment_detail_tv_show) {

    private lateinit var seasonsAdapter: SeasonsAdapter
    private val args: DetailTvShowFragmentArgs by navArgs()
    private val binding: FragmentDetailTvShowBinding by viewBinding(FragmentDetailTvShowBinding::bind, R.id.container_detail)
    private val mTvShowViewModel: DetailTvShowViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleViewModelObserver()
        mTvShowViewModel.getTvShow(args.id)
        binding.viewState.setOnRetakeClicked { mTvShowViewModel.getTvShow(args.id) }
    }

    private fun handleViewModelObserver() {
        mTvShowViewModel.response.observe(viewLifecycleOwner) {
            setupView(it)
        }
        mTvShowViewModel.viewState.observe(viewLifecycleOwner) {
            binding.viewState.handleViewState(it)
        }

        mTvShowViewModel.error.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { errorMsg ->
                binding.viewState.setErrorMessage(errorMsg)
            }
        }
    }

    private fun setupView(data: TvShow) {
        requireActivity().title = data.title
        binding.apply {
            val progress = data.rating ?: 0
            ivCover.load(data.getPosterPath()) {
                scale(Scale.FILL)
            }
            tvProgress.text = progress.toString()
            circularProgressBar.progress = progress.toFloat()
            tvTotalUserRating.text = getRating(data.totalUserRating ?: 0)
            tvReleaseDate.text = StringHelper.getDateForView(data.firstAirDate)
            tvSubDesc.text = getSubDesc(data.productionCompanies ?: listOf())
            seasonsAdapter = SeasonsAdapter(data.seasons ?: listOf())
            rvSeasons.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = seasonsAdapter
                if (itemDecorationCount == 0) {
                    addItemDecoration(PaddingItemDecoration(ViewUtil.dpToPx(16), true))
                }
            }
            tvDescription.text = data.overview
            data.genres?.forEach {
                val chip = Chip(requireContext())
                chip.text = it.name
                chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_secondary_dark))
                chip.chipBackgroundColor = ContextCompat.getColorStateList(requireContext(), R.color.color_secondary_light)
                chip.isClickable = false
                cgCategory.addView(chip)
            }
        }
    }

    private fun getRating(rating: Int): String {
        return "${rating.formatWithThousandComma()} Ratings"
    }

    private fun getSubDesc(data: List<ProductionCompanies>): SpannableStringBuilder {
        val generatedSubDesc = SpannableStringBuilder()
        generatedSubDesc.bold { appendLine("Production Company :") }
        val size = data.size
        data.forEachIndexed { index, productionCompany ->
            if (index == (size - 1)) {
                generatedSubDesc.append("${productionCompany.name}")
            } else {
                generatedSubDesc.append("${productionCompany.name}, ")
            }
        }
        return generatedSubDesc
    }
}