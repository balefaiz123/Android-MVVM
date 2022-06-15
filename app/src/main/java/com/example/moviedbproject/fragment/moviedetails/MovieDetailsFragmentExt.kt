package com.example.moviedbproject.fragment.moviedetails

import android.view.View
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun MovieDetailsFragment.observeLiveData() {
    binding.recycler.adapter = adapter
    vm.loadMovieDetail(movieDetails.movieId)

    vm.movieDetails.observe(this) {
        it.data?.let { movieDetails ->
            binding.detail = movieDetails
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it?.data?.backdrop_path}")
                .into(binding.backdropImage)
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it?.data?.poster_path}")
                .into(binding.posterImage)
        }
    }

    vm.movieTrailer.observe(this) {
        try {
            it.data?.results?.get(0)?.let { it1 ->
                videoTrailer(it1.key)
            }
        } catch (e: Exception) {
            binding.videoTrailer.visibility = View.GONE
        }

    }

    vm.movieReview.observe(this) {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
}

fun MovieDetailsFragment.videoTrailer(videoId: String) {
    val listener = object : AbstractYouTubePlayerListener() {

        override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
            super.onReady(youTubePlayer)
            youTubePlayer.cueVideo(videoId, 0f)

            val defaultPlayerUiController =
                DefaultPlayerUiController(binding.videoTrailer, youTubePlayer)
            binding.videoTrailer.setCustomPlayerUi(defaultPlayerUiController.rootView)
        }
    }

    val option = IFramePlayerOptions.Builder().controls(0).build()
    binding.videoTrailer.initialize(listener, option)
}
