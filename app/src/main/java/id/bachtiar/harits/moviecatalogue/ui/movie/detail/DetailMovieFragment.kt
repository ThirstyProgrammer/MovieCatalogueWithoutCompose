package id.bachtiar.harits.moviecatalogue.ui.movie.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.size.Scale
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.data.local.entity.MovieEntity
import id.bachtiar.harits.moviecatalogue.databinding.FragmentDetailMovieBinding
import id.bachtiar.harits.moviecatalogue.ui.MainActivity
import id.bachtiar.harits.moviecatalogue.util.StringHelper
import id.bachtiar.harits.moviecatalogue.util.formatWithThousandComma
import id.bachtiar.harits.moviecatalogue.util.handleViewState

@AndroidEntryPoint
class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private val args: DetailMovieFragmentArgs by navArgs()
    private val binding: FragmentDetailMovieBinding by viewBinding(FragmentDetailMovieBinding::bind, R.id.container_detail)
    private val mMovieViewModel: DetailMovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideMenu()
        handleViewModelObserver()
    }

    private fun handleViewModelObserver() {
        mMovieViewModel.getMovie(args.id).observe(viewLifecycleOwner) {
            binding.viewState.handleViewState(it.status, it.message.orEmpty())
            if (it.data != null) {
                setupView(it.data)
            }
        }
    }

    private fun setupView(data: MovieEntity) {
        requireActivity().title = data.title
        binding.apply {
            val progress = data.rating
            ivCover.load(data.poster) {
                scale(Scale.FILL)
            }
            tvProgress.text = progress.toString()
            circularProgressBar.progress = progress.toFloat()
            tvTotalUserRating.text = getRating(data.totalUserRating)
            tvReleaseDate.text = StringHelper.getDateForView(data.releaseDate)
            tvSubDesc.text = getSubDesc(data.productionCompanies)
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