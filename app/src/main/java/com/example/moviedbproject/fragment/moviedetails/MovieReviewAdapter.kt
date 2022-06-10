package com.example.moviedbproject.fragment.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.entity.moviereview.Result
import com.example.moviedbproject.R
import com.example.moviedbproject.databinding.MovieDetailsReviewItemBinding

class MovieReviewAdapter : PagingDataAdapter<Result, MovieReviewViewHolder>(diffUtil) {


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return true
            }
        }
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.details = data
        if (data?.author_details?.avatar_path != null){
            Glide.with(holder.binding.root)
                .load("https://image.tmdb.org/t/p/w500${data?.author_details?.avatar_path}")
                .into(holder.binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
            MovieDetailsReviewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}

class MovieReviewViewHolder(val binding: MovieDetailsReviewItemBinding) :
    RecyclerView.ViewHolder(binding.root)