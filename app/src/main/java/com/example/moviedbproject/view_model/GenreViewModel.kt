package com.example.moviedbproject.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.api_service.usecase.GenreUseCase
import com.example.common.entity.ResponseApp
import com.example.common.entity.genre.Genre
import com.example.common.entity.genre.GenreResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.common.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    application: Application,
    val genreUseCase: GenreUseCase,

) : BaseViewModel(application){

    val genreData = MutableLiveData<ResponseApp<GenreResponse>>()
    val selected = MutableLiveData<List<Genre>>(arrayListOf())

    init {
        loadAllGenre()
    }
    fun loadAllGenre(){
        viewModelScope.launch {
            genreUseCase.invoke().collect{
                genreData.postValue(it)
            }
        }
    }



}