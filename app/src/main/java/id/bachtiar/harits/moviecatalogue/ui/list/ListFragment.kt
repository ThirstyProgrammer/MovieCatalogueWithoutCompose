package id.bachtiar.harits.moviecatalogue.ui.list

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
import id.bachtiar.harits.moviecatalogue.databinding.FragmentListBinding
import id.bachtiar.harits.moviecatalogue.model.Category
import id.bachtiar.harits.moviecatalogue.model.Movie
import id.bachtiar.harits.moviecatalogue.ui.main.MainFragmentDirections
import id.bachtiar.harits.moviecatalogue.util.PaddingItemDecoration
import id.bachtiar.harits.moviecatalogue.util.ViewUtil

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), OnMovieClickCallback {

    private val mAdapter = MovieAdapter()
    private val binding: FragmentListBinding by viewBinding(FragmentListBinding::bind, R.id.container)
    private val mViewModel: ListViewModel by viewModels()

    companion object {
        private const val CATEGORY_DATA = "category_data"

        fun newInstance(category: Category): ListFragment {
            val fragment = ListFragment()
            fragment.arguments = bundleOf(
                CATEGORY_DATA to category
            )
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireArguments().containsKey(CATEGORY_DATA)) {
            mViewModel.category = requireArguments().getParcelable(CATEGORY_DATA) ?: Category()
        }
    }

    override fun onItemClicked(data: Movie) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(data)
        parentFragment?.findNavController()?.navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())

            rvList.apply {
                setHasFixedSize(true)
                layoutManager = linearLayoutManager
                adapter = mAdapter
                if (itemDecorationCount == 0) {
                    addItemDecoration(PaddingItemDecoration(ViewUtil.dpToPx(16)))
                }
            }
        }
        mAdapter.setOnMovieClickCallback(this)
        mAdapter.setData(mViewModel.category.value ?: listOf())
    }
}