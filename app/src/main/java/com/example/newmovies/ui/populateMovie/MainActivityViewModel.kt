package com.example.newmovies.ui.populateMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.newmovies.data.model.Result
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private var repository: MoviePagedListRepository):ViewModel() {

    private var compositeDisposable = CompositeDisposable()

val moviePagedList:LiveData<PagedList<Result>> by lazy {
  repository.fetchLiveMoviePagedList(compositeDisposable)
}



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}