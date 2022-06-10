package com.example.moviedbproject.fragment.moviebygenre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbproject.R
import com.example.moviedbproject.databinding.LayoutLoadingBinding
import com.example.moviedbproject.databinding.MovieByGenreBinding

class DiscoverMovieStateAdapter() : LoadStateAdapter<DiscoverStateViewHolder>() {
    override fun onBindViewHolder(holder: DiscoverStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): DiscoverStateViewHolder =
        DiscoverStateViewHolder(
            LayoutLoadingBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
}

class DiscoverStateViewHolder(val binding: LayoutLoadingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                binding.button.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
            is LoadState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.button.visibility = View.GONE
            }
            is LoadState.NotLoading -> {
                binding.progressBar.visibility = View.GONE
                binding.button.visibility = View.GONE
            }
        }
    }
}