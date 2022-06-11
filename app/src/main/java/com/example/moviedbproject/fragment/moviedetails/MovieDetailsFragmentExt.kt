package com.example.moviedbproject.fragment.moviedetails

import android.util.Log
import com.bumptech.glide.Glide
import com.example.moviedbproject.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun MovieDetailsFragment.observeLiveData(){
    binding.recycler.adapter = adapter
    vm.loadMovieDetail(movieDetails.movieId)

    vm.movieDetails.observe(this){
        it.data?.let {movieDetails->
            binding.detail = movieDetails
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it?.data?.backdrop_path}")
                .into(binding.poster)

        }

    }

    vm.movieTrailer.observe(this){
        it.data?.let {data->
            videoTrailer(data.results[0].key)
        }
    }

    vm.movieReview.observe(this){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
}

fun MovieDetailsFragment.videoTrailer(videoId:String) {
    val youtubeFragment = YouTubePlayerSupportFragmentX.newInstance()
    with(parentFragmentManager) {
        beginTransaction().apply {
            add(R.id.video_trailer, youtubeFragment)
            commit()
        }
    }
    youtubeFragment.initialize("AIzaSyAUGz7-k_vZw9l0wxkH6ljA1GyfeArhgnY",
        object : YouTubePlayerSupportFragmentX.OnInitializedListener() {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.cueVideo(videoId)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.e("youtubePlayer", "error ${p1?.name}")
            }
        })
}
