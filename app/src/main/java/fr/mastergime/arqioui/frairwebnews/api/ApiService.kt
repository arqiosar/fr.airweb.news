package fr.mastergime.arqioui.frairwebnews.api

import fr.mastergime.arqioui.frairwebnews.api.URLs.Companion.NEWS_URL
import fr.mastergime.arqioui.frairwebnews.exceptions.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private val retrofit by lazy{
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(NEWS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API : WebServiceApi by lazy{
        retrofit.create(WebServiceApi::class.java)
    }

    private val networkInterceptor = NetworkConnectionInterceptor()

    private val okHttpClient =
        OkHttpClient.Builder().addInterceptor(networkInterceptor).build()

}