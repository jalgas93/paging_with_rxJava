package com.example.newmovies.data.api

import com.example.newmovies.const.Constant.Companion.API_KEY
import com.example.newmovies.data.model.MoviePopular
import com.example.newmovies.data.model.Result
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {


    @GET("3/movie/popular")
    fun getPopularMovie(
        @Query("page")page:Int = 1,
        @Query("language")language:String = "ru",

        @Query("api_key")api_key:String = API_KEY

        ): Single<MoviePopular>
}