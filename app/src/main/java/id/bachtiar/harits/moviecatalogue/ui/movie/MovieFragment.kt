package id.bachtiar.harits.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.data.ViewState
import id.bachtiar.harits.moviecatalogue.data.local.entity.MoviesEntity
import id.bachtiar.harits.moviecatalogue.databinding.FragmentMovieBinding
import id.bachtiar.harits.moviecatalogue.ui.MainViewModel
import id.bachtiar.harits.moviecatalogue.ui.main.MainFragmentDirections
import id.bachtiar.harits.moviecatalogue.util.PaddingItemDecoration
import id.bachtiar.harits.moviecatalogue.util.ViewUtil
import id.bachtiar.harits.moviecatalogue.util.handleViewState

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie), OnMovieClickCallback {

    private val binding: FragmentMovieBinding by viewBinding(FragmentMovieBinding::bind, R.id.container)
    private val mViewModel: MovieViewModel by viewModels()
    private val mSharedViewModel: MainViewModel by activityViewModels()
    private var mAdapter: MovieAdapter? = null

    override fun onItemClicked(data: MoviesEntity?) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailMovieFragment(data?.movieId ?: 0)
        parentFragment?.findNavController()?.navigate(direction)
    }

    override fun onFavouriteClicked(data: MoviesEntity?) {
        if (data != null) {
            mViewModel.updateFavorite(data)
            val text = if (data.isFavourite) {
                getString(R.string.favorite_removed)
            } else {
                getString(R.string.favorite_added)
            }
            Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        handleViewModelObserver()
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
        }
    }

    private fun handleViewModelObserver() {
        mSharedViewModel.queryAndFavorite.observe(viewLifecycleOwner) { queryAndFavorite ->
            mAdapter = null
            binding.rvMovie.adapter = null
            if (queryAndFavorite.second) {
                mViewModel.getFavoriteMovies(queryAndFavorite.first).observe(viewLifecycleOwner) {
                    binding.viewState.handleViewState(
                        ViewState.SUCCESS,
                        getString(R.string.empty),
                        getString(R.string.not_has_favorite_movies),
                        isEmpty = it.isEmpty()
                    )
                    if (it != null) {
                        mAdapter = MovieAdapter()
                        mAdapter?.setOnMovieClickCallback(this)
                        mAdapter?.submitList(it)
                        binding.rvMovie.adapter = mAdapter
                    }
                }
            }else{
                mViewModel.getPopularMovies(queryAndFavorite.first).observe(viewLifecycleOwner) {
                    binding.viewState.handleViewState(it.status, it.message.orEmpty())
                    if (it.data != null) {
                        mAdapter = MovieAdapter()
                        mAdapter?.setOnMovieClickCallback(this)
                        mAdapter?.submitList(it.data)
                        binding.rvMovie.adapter = mAdapter
                    }
                }
            }
        }
    }
}