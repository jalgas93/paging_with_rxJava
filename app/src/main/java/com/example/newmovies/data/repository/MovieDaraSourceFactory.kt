package com.example.newmovies.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.newmovies.data.api.MovieApi
import com.example.newmovies.data.model.Result
import io.reactivex.disposables.CompositeDisposable

class MovieDaraSourceFactory(
    private var apiService: MovieApi,
    private var compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Result>() {

    var movieLiveDataSource = MutableLiveData<MovieDataSource>()
    override fun create(): DataSource<Int, Result> {
        var movieDataSource = MovieDataSource(apiService, compositeDisposable)
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}