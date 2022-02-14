package id.bachtiar.harits.moviecatalogue.ui.tvshow

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
import id.bachtiar.harits.moviecatalogue.data.local.entity.TvShowsEntity
import id.bachtiar.harits.moviecatalogue.databinding.FragmentTvShowBinding
import id.bachtiar.harits.moviecatalogue.ui.MainViewModel
import id.bachtiar.harits.moviecatalogue.ui.main.MainFragmentDirections
import id.bachtiar.harits.moviecatalogue.util.PaddingItemDecoration
import id.bachtiar.harits.moviecatalogue.util.ViewUtil
import id.bachtiar.harits.moviecatalogue.util.handleViewState

@AndroidEntryPoint
class TvShowFragment : Fragment(R.layout.fragment_tv_show), OnTvShowClickCallback {

    private val binding: FragmentTvShowBinding by viewBinding(FragmentTvShowBinding::bind, R.id.container)
    private val mViewModel: TvShowViewModel by viewModels()
    private val mSharedViewModel: MainViewModel by activityViewModels()
    private var mAdapter : TvShowAdapter? = null

    override fun onItemClicked(data: TvShowsEntity?) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailTvShowFragment(data?.tvShowId ?: 0)
        parentFragment?.findNavController()?.navigate(direction)
    }

    override fun onFavouriteClicked(data: TvShowsEntity?) {
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
            rvTvShow.apply {
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
            binding.rvTvShow.adapter = null
            if (queryAndFavorite.second) {
                mViewModel.getFavoriteTvShows(queryAndFavorite.first).observe(viewLifecycleOwner) {
                    binding.viewState.handleViewState(
                        ViewState.SUCCESS,
                        getString(R.string.empty),
                        getString(R.string.not_has_favorite_tv_shows),
                        isEmpty = it.isEmpty()
                    )
                    if (it != null) {
                        mAdapter = TvShowAdapter()
                        mAdapter?.setOnMovieClickCallback(this)
                        mAdapter?.submitList(it)
                        binding.rvTvShow.adapter = mAdapter
                    }
                }
            }else{
                mViewModel.getPopularTvShows(queryAndFavorite.first).observe(viewLifecycleOwner) {
                    binding.viewState.handleViewState(it.status, it.message.orEmpty())
                    if (it.data != null) {
                        mAdapter = TvShowAdapter()
                        mAdapter?.setOnMovieClickCallback(this)
                        mAdapter?.submitList(it.data)
                        binding.rvTvShow.adapter = mAdapter
                    }
                }
            }
        }
    }
}