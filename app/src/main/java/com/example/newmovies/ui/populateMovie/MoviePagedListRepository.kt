package com.example.newmovies.ui.populateMovie

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.newmovies.const.Constant.Companion.END_PAGE
import com.example.newmovies.data.api.MovieApi
import com.example.newmovies.data.model.Result
import com.example.newmovies.data.repository.MovieDaraSourceFactory
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository(private var apiService:MovieApi) {
    lateinit var moviePagedList:LiveData<PagedList<Result>>
    lateinit var mFactory:MovieDaraSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable):LiveData<PagedList<Result>>{
           mFactory = MovieDaraSourceFactory(apiService, compositeDisposable)

        val config =PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(END_PAGE)
            .build()
moviePagedList = LivePagedListBuilder(mFactory,config).build()
        return moviePagedList
    }


}