package com.example.newmovies.ui.populateMovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newmovies.R
import com.example.newmovies.const.Constant.Companion.POSTER_BASE_URL
import com.example.newmovies.data.model.Result
import kotlinx.android.synthetic.main.movie_item.view.*

class MainActivityMovieAdapter(private var context: Context) :
    PagedListAdapter<Result, RecyclerView.ViewHolder>(MovieDiffCallBack()) {

    class MovieDiffCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == oldItem
        }
    }


    class PopularMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Result?, context: Context) {
            itemView.cv_movie_title.text = movie?.title
            itemView.cv_movie_release_date.text = movie?.releaseDate

            val moviePhoto = POSTER_BASE_URL+movie?.backdropPath

            Glide.with(itemView.context)
                .load(moviePhoto).into(itemView.cv_iv_movie_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return PopularMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PopularMovieViewHolder).bind(getItem(position), context)
    }
}