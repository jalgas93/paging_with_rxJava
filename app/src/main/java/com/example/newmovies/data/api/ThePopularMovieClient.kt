package com.example.newmovies.data.api

import com.example.newmovies.const.Constant.Companion.API_KEY
import com.example.newmovies.const.Constant.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ThePopularMovieClient {


    fun getClient(): MovieApi {
        val requestInteseptor = Interceptor { chain ->

            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_Key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)

        }

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(requestInteseptor)
    .connectTimeout(30,TimeUnit.SECONDS)
    .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}