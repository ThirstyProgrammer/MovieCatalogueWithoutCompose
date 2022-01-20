package id.bachtiar.harits.moviecatalogue.ui.main

import id.bachtiar.harits.moviecatalogue.model.Category
import id.bachtiar.harits.moviecatalogue.model.Data
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var data: Data

    @Before
    fun init() {
        mainViewModel = MainViewModel()
        data = Data(
            listOf(
                Category("Movie", listOf()),
                Category("TV Show", listOf()),
            )
        )
    }

    @Test
    fun getData() {
        assertNotNull(mainViewModel.data)
    }

    @Test
    fun setData() {
        mainViewModel.data = data
        assertEquals(data, mainViewModel.data)
    }
}