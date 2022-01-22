package id.bachtiar.harits.moviecatalogue.ui.main

import id.bachtiar.harits.moviecatalogue.model.Data
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var data: Data

    @Before
    fun init() {
        mainViewModel = MainViewModel()
        data = Data(
            movies = listOf(),
            tvShows = listOf()
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