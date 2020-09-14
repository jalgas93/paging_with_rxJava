package com.example.newmovies.ui.populateMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedListAdapter
import com.example.newmovies.R
import com.example.newmovies.data.api.MovieApi
import com.example.newmovies.data.api.ThePopularMovieClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainActivityViewModel
    lateinit var mRepos: MoviePagedListRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiService: MovieApi = ThePopularMovieClient.getClient()
        mRepos = MoviePagedListRepository(apiService)

        mViewModel = getMovieModel()

        val movieAdapter = MainActivityMovieAdapter(this)

        rv_movie_list.adapter = movieAdapter

        mViewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })


    }

    private fun getMovieModel(): MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                @Suppress("UNCHECED")
                return MainActivityViewModel(mRepos) as T
            }

        })[MainActivityViewModel::class.java]
    }


}