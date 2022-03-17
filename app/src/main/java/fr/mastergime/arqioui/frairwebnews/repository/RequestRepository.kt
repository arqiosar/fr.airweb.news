package fr.mastergime.arqioui.frairwebnews.repository

import fr.mastergime.arqioui.frairwebnews.api.ApiService
import fr.mastergime.arqioui.frairwebnews.model.responses.News
import fr.mastergime.arqioui.frairwebnews.model.responses.NewsList
import retrofit2.Response

class RequestRepository {

    suspend fun getNews(): Response<NewsList> {
        return ApiService.API.getNews()
    }
}