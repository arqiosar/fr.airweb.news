package fr.mastergime.arqioui.frairwebnews.model.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mastergime.arqioui.frairwebnews.model.responses.NewsList
import fr.mastergime.arqioui.frairwebnews.repository.RequestRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class NewsViewModel(private val repository: RequestRepository) : ViewModel() {

    var newsResponse: MutableLiveData<Response<NewsList>> = MutableLiveData()

    fun getNews() {
        viewModelScope.launch() {
            val response = repository.getNews()
            newsResponse.value = response
        }

    }

}