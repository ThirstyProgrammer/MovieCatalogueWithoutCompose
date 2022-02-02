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
import id.bachtiar.harits.moviecatalogue.util.*

@AndroidEntryPoint
class TvShowFragment : Fragment(R.layout.fragment_tv_show), OnTvShowClickCallback {

    private lateinit var mAdapter : TvShowAdapter
    private val binding: FragmentTvShowBinding by viewBinding(FragmentTvShowBinding::bind, R.id.container)
    private val mViewModel: TvShowViewModel by viewModels()
    private var isLoading: Boolean = false

    override fun onItemClicked(data: TvShows.Data) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailTvShowFragment(data.id ?: 0)
        parentFragment?.findNavController()?.navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        handleViewModelObserver()
        mViewModel.getPopularTvShows()
    }

    private fun handleViewModelObserver() {
        mViewModel.response.observe(viewLifecycleOwner) {
            isLoading = false
            mAdapter = TvShowAdapter(it.results ?: listOf())
            mAdapter.setOnMovieClickCallback(this)
            binding.rvTvShow.adapter = mAdapter
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