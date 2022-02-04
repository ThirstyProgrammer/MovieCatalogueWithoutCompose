package id.bachtiar.harits.moviecatalogue.ui.movie.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.size.Scale
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.FragmentDetailMovieBinding
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.model.ProductionCompanies
import id.bachtiar.harits.moviecatalogue.util.*

@AndroidEntryPoint
class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private val args: DetailMovieFragmentArgs by navArgs()
    private val binding: FragmentDetailMovieBinding by viewBinding(FragmentDetailMovieBinding::bind, R.id.container_detail)
    private val mMovieViewModel: DetailMovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleViewModelObserver()
        binding.viewState.setOnRetakeClicked { mMovieViewModel.getMovie(args.id) }
    }

    private fun handleViewModelObserver() {
        mMovieViewModel.getMovie(args.id).observe(viewLifecycleOwner) {
            binding.viewState.handleViewState(it.status, it.message.orEmpty())
            if (it.data != null) {
                setupView(it.data)
            }
        }
    }

    private fun setupView(data: Movie) {
        requireActivity().title = data.title
        binding.apply {
            val progress = data.rating ?: 0
            ivCover.load(data.getPosterPath()) {
                scale(Scale.FILL)
            }
            tvProgress.text = progress.toString()
            circularProgressBar.progress = progress.toFloat()
            tvTotalUserRating.text = getRating(data.totalUserRating ?: 0)
            tvReleaseDate.text = StringHelper.getDateForView(data.releaseDate)
            tvSubDesc.text = getSubDesc(data.productionCompanies ?: listOf())
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