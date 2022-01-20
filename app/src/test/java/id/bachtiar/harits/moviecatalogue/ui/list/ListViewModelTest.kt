package id.bachtiar.harits.moviecatalogue.ui.list

import id.bachtiar.harits.moviecatalogue.model.Category
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class ListViewModelTest {

    private lateinit var listViewModel: ListViewModel
    private lateinit var category: Category

    @Before
    fun init() {
        listViewModel = ListViewModel()
        category = Category(
            key = "Movie",
            value = listOf()
        )
    }

    @Test
    fun getCategory() {
        assertNotNull(listViewModel.category)
    }

    @Test
    fun setCategory() {
        listViewModel.category = category
        assertEquals(category, listViewModel.category)
    }
}