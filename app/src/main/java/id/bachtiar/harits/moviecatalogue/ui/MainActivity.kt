package id.bachtiar.harits.moviecatalogue.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import id.bachtiar.harits.moviecatalogue.R
import id.bachtiar.harits.moviecatalogue.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private val mViewModel: MainViewModel by viewModels()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        setupSearchView(menu)
        menu.forEach {
            when (it.title) {
                getString(R.string.favorite) -> it.isVisible = false
            }
        }
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                mViewModel.updateFavoriteState(false)
                item.isVisible = false
                menu?.forEach {
                    when (it.title) {
                        getString(R.string.normal) -> it.isVisible = true
                    }
                }
            }
            R.id.normal -> {
                mViewModel.updateFavoriteState(true)
                item.isVisible = false
                menu?.forEach {
                    when (it.title) {
                        getString(R.string.favorite) -> it.isVisible = true
                    }
                }
            }
            else -> Unit
        }
        return true
    }

    fun showMenu() {
        val isFavorite = mViewModel.queryAndFavorite.value?.second ?: false
        menu?.forEach {
            when (it.title) {
                getString(R.string.normal) -> it.isVisible = !isFavorite
                getString(R.string.favorite) -> it.isVisible = isFavorite
                else -> it.isVisible = true
            }
        }
    }

    fun hideMenu() {
        menu?.forEach {
            it.isVisible = false
        }
    }

    private fun setupSearchView(menu: Menu?) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        val searchEditText = searchView.findViewById<EditText>(R.id.search_src_text)

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.background = ContextCompat.getDrawable(this, R.drawable.bg_search)
        searchView.queryHint = getString(R.string.search_hint)
        searchEditText.setTextColor(ContextCompat.getColor(this, R.color.color_primary))
        searchEditText.setHintTextColor(ContextCompat.getColor(this, R.color.color_primary_light))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mViewModel.updateQuery(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mViewModel.updateQuery(newText.orEmpty())
                return false
            }

        })
    }
}