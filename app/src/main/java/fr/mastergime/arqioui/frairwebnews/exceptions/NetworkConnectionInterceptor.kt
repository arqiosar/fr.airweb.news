package fr.mastergime.arqioui.frairwebnews.exceptions

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

class NetworkConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        request = request.newBuilder()
            .method(request.method, request.body)
            .build()

        return try {
            chain.proceed(request)
        } catch (ex: IOException) {
            Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1)
                .message("No internet connection.").code(500).body(
                    ResponseBody.create(
                        "application/json; charset=utf-8".toMediaTypeOrNull(),
                        ""
                    )
                ).build()
        }

    }
}