package id.bachtiar.harits.moviecatalogue.ui.movie

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
import id.bachtiar.harits.moviecatalogue.databinding.FragmentMovieBinding
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.ui.main.MainFragmentDirections
import id.bachtiar.harits.moviecatalogue.util.PaddingItemDecoration
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie), OnMovieClickCallback {

    private val mAdapter = MovieAdapter()
    private val binding: FragmentMovieBinding by viewBinding(FragmentMovieBinding::bind, R.id.container)
    private val mViewModel: MovieViewModel by viewModels()

    companion object {
        private const val MOVIES_DATA = "movies_data"

        fun newInstance(movies: List<Movie>): MovieFragment {
            val fragment = MovieFragment()
            fragment.arguments = bundleOf(
                MOVIES_DATA to movies
            )
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(MOVIES_DATA)) {
            mViewModel.movies.addAll((requireArguments().getParcelableArrayList<Movie>(MOVIES_DATA))?.toTypedArray() ?: emptyArray())
        }
    }

    override fun onItemClicked(data: Movie) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailMovieFragment(data)
        parentFragment?.findNavController()?.navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())

            rvMovie.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                adapter = mAdapter
                if (itemDecorationCount == 0) {
                    addItemDecoration(PaddingItemDecoration(ViewUtil.dpToPx(16)))
                }
            }
        }
        mAdapter.setOnMovieClickCallback(this)
        mAdapter.setData(mViewModel.movies)
    }
}