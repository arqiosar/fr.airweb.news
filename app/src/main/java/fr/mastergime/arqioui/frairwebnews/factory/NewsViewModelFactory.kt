package fr.mastergime.arqioui.frairwebnews.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.mastergime.arqioui.frairwebnews.model.viewmodels.NewsViewModel
import fr.mastergime.arqioui.frairwebnews.repository.RequestRepository

class NewsViewModelFactory(
    private val repository: RequestRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}