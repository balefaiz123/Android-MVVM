package com.example.moviedbproject.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.api_service.usecase.GetMovieDetailsUseCase
import com.example.api_service.usecase.MovieReviewUseCase
import com.example.api_service.usecase.MovieVideoUseCase
import com.example.common.entity.ResponseApp
import com.example.common.entity.movie_detail.MovieDetailsResponse
import com.example.common.entity.movie_trailer.MovieVideoResponse
import com.example.common.entity.moviereview.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.common.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    application: Application,
    val movieDetailsUseCase: GetMovieDetailsUseCase,
    val movieReviewUseCase: MovieReviewUseCase,
    val movieVideoUseCase : MovieVideoUseCase
) : BaseViewModel(application){

    val movieDetails = MutableLiveData<ResponseApp<MovieDetailsResponse>>()
    val movieReview = MutableLiveData<PagingData<Result>>()
    val movieTrailer = MutableLiveData<ResponseApp<MovieVideoResponse>>()

    fun loadMovieDetail(selectedMovie : Int){
        viewModelScope.launch {
            movieDetailsUseCase.invoke(selectedMovie).collect{
                movieDetails.postValue(it)
            }
        }
        viewModelScope.launch {
            movieReviewUseCase.invoke(selectedMovie.toString()).collect{
                movieReview.postValue(it)
            }
        }

        viewModelScope.launch {
            movieVideoUseCase.invoke(selectedMovie).collect{
                movieTrailer.postValue(it)
            }
        }


    }
}