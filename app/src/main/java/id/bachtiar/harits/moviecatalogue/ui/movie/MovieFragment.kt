package id.bachtiar.harits.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.FragmentMovieBinding
import id.bachtiar.harits.moviecatalogue.model.Movies
import id.bachtiar.harits.moviecatalogue.ui.main.MainFragmentDirections
import id.bachtiar.harits.moviecatalogue.util.*

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie), OnMovieClickCallback {

    private lateinit var mAdapter: MovieAdapter
    private val binding: FragmentMovieBinding by viewBinding(FragmentMovieBinding::bind, R.id.container)
    private val mViewModel: MovieViewModel by viewModels()
    private var isLoading: Boolean = false
    private var isFirstHit: Boolean = true

    override fun onItemClicked(data: Movies.Data) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailMovieFragment(data.id ?: 0)
        parentFragment?.findNavController()?.navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        handleViewModelObserver()
        if (isFirstHit){
            mViewModel.getPopularMovies()
            isFirstHit = false
        }
    }

    private fun setupView() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvMovie.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                if (itemDecorationCount == 0) {
                    addItemDecoration(PaddingItemDecoration(ViewUtil.dpToPx(16)))
                }
            }
            viewState.setOnRetakeClicked { mViewModel.getPopularMovies() }
        }
    }

    private fun handleViewModelObserver() {
        mViewModel.response.observe(viewLifecycleOwner) {
            isLoading = false
            mAdapter = MovieAdapter(it.results ?: listOf())
            mAdapter.setOnMovieClickCallback(this)
            binding.rvMovie.adapter = mAdapter
        }
        mViewModel.viewState.observe(viewLifecycleOwner) {
            if (!isLoading) binding.viewState.handleViewState(it)
        }

        mViewModel.error.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { errorMsg ->
                if (!isLoading) binding.viewState.setErrorMessage(errorMsg)
            }
        }
    }
}