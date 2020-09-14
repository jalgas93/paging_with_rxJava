package com.example.newmovies.data.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.newmovies.const.Constant.Companion.FIRST_PAGE
import com.example.newmovies.data.api.MovieApi
import com.example.newmovies.data.api.ThePopularMovieClient
import com.example.newmovies.data.model.MoviePopular
import com.example.newmovies.data.model.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private var apiService: MovieApi,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Result>() {


    private  var page = FIRST_PAGE
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
             compositeDisposable.add(
                 apiService.getPopularMovie(page)
                     .subscribeOn(Schedulers.io())
                     .subscribe({
                        callback.onResult(it.results,null, page+1)

                     },{
                      Log.e("PopularMovieError",it.message!!)
                     })
             )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        compositeDisposable.add(
            apiService.getPopularMovie(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe({

                    if (it.totalPages>=params.key)
                    callback.onResult(it.results,params.key+1)

                },{
                    Log.e("PopularMovieError",it.message!!)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        TODO("Not yet implemented")
    }
}