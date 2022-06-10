package com.example.moviedbproject.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.api_service.usecase.MovieByGenreUseCase
import com.example.common.entity.ResponseApp
import com.example.common.entity.movie_discover.MovieDiscoverResponse
import com.example.common.entity.movie_discover.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import id.indocyber.common.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieByGenreViewModel @Inject constructor(
    application: Application,
    val discoverMovieByGenreUseCase: MovieByGenreUseCase
) :  BaseViewModel(application) {

    val dataMovieByGenre = MutableLiveData<PagingData<Result>>()

    fun loadAllMovieByGenre(genre:String){
        viewModelScope.launch {
            discoverMovieByGenreUseCase.invoke(genre).collect{
                dataMovieByGenre.postValue(it)
            }
        }
    }
}