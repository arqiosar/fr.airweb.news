package fr.mastergime.arqioui.frairwebnews.api

import fr.mastergime.arqioui.frairwebnews.model.responses.NewsList
import retrofit2.Response
import retrofit2.http.GET

interface WebServiceApi {

    @GET("psg/psg.json")
    suspend fun getNews(): Response<NewsList>

}