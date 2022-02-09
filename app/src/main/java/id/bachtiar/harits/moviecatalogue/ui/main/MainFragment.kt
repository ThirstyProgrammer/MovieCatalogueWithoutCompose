package id.bachtiar.harits.moviecatalogue.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.ui.MainActivity
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.FragmentMainBinding
import id.bachtiar.harits.moviecatalogue.ui.movie.MovieFragment
import id.bachtiar.harits.moviecatalogue.ui.tvshow.TvShowFragment
import id.bachtiar.harits.moviecatalogue.util.ViewPagerAdapter

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind, R.id.container)
    private val titlesViewPager: ArrayList<String> = arrayListOf()
    private val fragmentsViewPager: ArrayList<Fragment> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showMenu()
        requireActivity().title = getString(R.string.app_name)
        setupViewPager()
    }

    private fun setupViewPager() {
        if (titlesViewPager.isEmpty()) {
            titlesViewPager.add(getString(R.string.movies))
            fragmentsViewPager.add(MovieFragment())
            titlesViewPager.add(getString(R.string.tv_shows))
            fragmentsViewPager.add(TvShowFragment())
        }
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle, fragmentsViewPager)
        binding.apply {
            viewPager.apply {
                if (adapter == null) {
                    adapter = viewPagerAdapter
                    offscreenPageLimit = fragmentsViewPager.size

                }
            }
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = titlesViewPager[position]
            }.attach()
        }
    }
}