package id.bachtiar.harits.moviecatalogue.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _queryAndFavorite: MutableLiveData<Pair<String, Boolean>> = MutableLiveData()
    val queryAndFavorite: LiveData<Pair<String, Boolean>> = _queryAndFavorite

    init {
        _queryAndFavorite.postValue(Pair("", false))
    }

    fun updateFavoriteState(isFavorite: Boolean) {
        _queryAndFavorite.postValue(Pair(_queryAndFavorite.value?.first ?: "", isFavorite))
    }

    fun updateQuery(query: String) {
        _queryAndFavorite.postValue(Pair(query, _queryAndFavorite.value?.second ?: false))
    }
}