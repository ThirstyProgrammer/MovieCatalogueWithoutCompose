package id.bachtiar.harits.moviecatalogue.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.FragmentMainBinding
import id.bachtiar.harits.moviecatalogue.model.Data
import id.bachtiar.harits.moviecatalogue.ui.list.ListFragment
import id.bachtiar.harits.moviecatalogue.util.ViewPagerAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.ArrayList

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind, R.id.container)
    private val mViewModel: MainViewModel by viewModels()
    private val json = Json { ignoreUnknownKeys = true }

    private val titlesViewPager: ArrayList<String> = arrayListOf()
    private val fragmentsViewPager: ArrayList<Fragment> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.data = getData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.app_name)
        setupViewPager()
    }

    private fun setupViewPager() {
        if (titlesViewPager.isEmpty()) {
            mViewModel.data.categories?.forEach {
                titlesViewPager.add(it.key.orEmpty())
                fragmentsViewPager.add(ListFragment.newInstance(it))
            }
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

    private fun getData(): Data {
        val jsonString = requireActivity().assets.open("moviecatalogue.json").bufferedReader().use { it.readText() }
        return json.decodeFromString(jsonString)
    }
}