package id.bachtiar.harits.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.FragmentTvShowBinding
import id.bachtiar.harits.moviecatalogue.model.TvShows
import id.bachtiar.harits.moviecatalogue.ui.main.MainFragmentDirections
import id.bachtiar.harits.moviecatalogue.util.PaddingItemDecoration
import id.bachtiar.harits.moviecatalogue.util.ViewUtil
import id.bachtiar.harits.moviecatalogue.util.handleViewState
import id.bachtiar.harits.moviecatalogue.util.setOnRetakeClicked

@AndroidEntryPoint
class TvShowFragment : Fragment(R.layout.fragment_tv_show), OnTvShowClickCallback {

    private lateinit var mAdapter : TvShowAdapter
    private val binding: FragmentTvShowBinding by viewBinding(FragmentTvShowBinding::bind, R.id.container)
    private val mViewModel: TvShowViewModel by viewModels()

    override fun onItemClicked(data: TvShows.Data) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailTvShowFragment(data.id ?: 0)
        parentFragment?.findNavController()?.navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        handleViewModelObserver()
    }

    private fun handleViewModelObserver() {
        mViewModel.getPopularTvShows().observe(viewLifecycleOwner) {
            binding.viewState.handleViewState(it.status, it.message.orEmpty())
            if (it.data != null) {
                mAdapter = TvShowAdapter(it.data.results ?: listOf())
                mAdapter.setOnMovieClickCallback(this)
                binding.rvTvShow.adapter = mAdapter
            }
        }
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
            viewState.setOnRetakeClicked { mViewModel.getPopularTvShows() }
        }
    }
}