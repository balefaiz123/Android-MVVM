package com.example.moviedbproject.fragment.moviedetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.common.base.BaseFragment
import com.example.moviedbproject.R
import com.example.moviedbproject.databinding.MovieDetailsBinding
import com.example.moviedbproject.view_model.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

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