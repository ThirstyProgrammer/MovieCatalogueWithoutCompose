package id.bachtiar.harits.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.FragmentTvShowBinding
import id.bachtiar.harits.moviecatalogue.model.TvShow
import id.bachtiar.harits.moviecatalogue.ui.main.MainFragmentDirections
import id.bachtiar.harits.moviecatalogue.util.PaddingItemDecoration
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

@AndroidEntryPoint
class TvShowFragment : Fragment(R.layout.fragment_tv_show), OnTvShowClickCallback {

    private val mAdapter = TvShowAdapter()
    private val binding: FragmentTvShowBinding by viewBinding(FragmentTvShowBinding::bind, R.id.container)
    private val mViewModel: TvShowViewModel by viewModels()

    companion object {
        private const val TV_SHOWS_DATA = "tv_shows_data"

        fun newInstance(tvShows: List<TvShow>): TvShowFragment {
            val fragment = TvShowFragment()
            fragment.arguments = bundleOf(
                TV_SHOWS_DATA to tvShows
            )
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(TV_SHOWS_DATA)) {
            mViewModel.tvShows.addAll((requireArguments().getParcelableArrayList<TvShow>(TV_SHOWS_DATA))?.toTypedArray() ?: emptyArray())
        }
    }

    override fun onItemClicked(data: TvShow) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailTvShowFragment(data)
        parentFragment?.findNavController()?.navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())

            rvTvShow.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                adapter = mAdapter
                if (itemDecorationCount == 0) {
                    addItemDecoration(PaddingItemDecoration(ViewUtil.dpToPx(16)))
                }
            }
        }
        mAdapter.setOnMovieClickCallback(this)
        mAdapter.setData(mViewModel.tvShows)
    }
}