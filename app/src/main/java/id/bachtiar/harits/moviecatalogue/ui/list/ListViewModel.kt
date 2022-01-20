package id.bachtiar.harits.moviecatalogue.ui.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bachtiar.harits.moviecatalogue.model.Category
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(): ViewModel() {

    var category = Category()
}