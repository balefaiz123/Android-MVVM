package com.example.moviedbproject.fragment.moviedetails

import android.util.Log
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.common.base.BaseFragment
import com.example.moviedbproject.R
import com.example.moviedbproject.databinding.MovieDetailsBinding
import com.example.moviedbproject.view_model.MovieDetailsViewModel
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel,MovieDetailsBinding>(){
    override val vm: MovieDetailsViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.movie_details
    val adapter = MovieReviewAdapter()
    val movieDetails : MovieDetailsFragmentArgs by navArgs()

    override fun initBinding(binding: MovieDetailsBinding) {
        super.initBinding(binding)


        observeLiveData()


    }
}