package com.example.moviedbproject.fragment.moviebygenre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.entity.movie_discover.Result
import com.example.moviedbproject.databinding.MovieByGenreItemBinding

class MovieByGenreAdapter(
    val navigateToDetails : (Int) -> Unit
) : PagingDataAdapter<Result, MovieByGenreViewHolder>(diffUtil) {

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return true
            }
        }
    }

    override fun onBindViewHolder(holder: MovieByGenreViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.movieByGenre = data
        holder.binding.root.setOnClickListener {
            data?.let {
                navigateToDetails(it.id) }
        }
        Glide.with(holder.binding.root)
            .load("https://image.tmdb.org/t/p/w500${data?.poster_path}")
            .into(holder.binding.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByGenreViewHolder {
        return MovieByGenreViewHolder(
            MovieByGenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false
        ))
    }
}

class MovieByGenreViewHolder(val binding : MovieByGenreItemBinding) : RecyclerView.ViewHolder(binding.root){

}