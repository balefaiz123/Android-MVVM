package com.example.api_service.usecase

import com.example.api_service.service.GetMovieDetailsService
import com.example.common.entity.ResponseApp
import com.example.common.entity.movie_detail.MovieDetailsResponse
import kotlinx.coroutines.flow.flow

class GetMovieDetailsUseCase(val getMovieDetailsService: GetMovieDetailsService) {

    operator fun invoke(movie: Int) = flow{
        try {
            emit(ResponseApp.loading())
            val movieDetails = getMovieDetailsService.getMovieDetails(movie.toString())
            if (movieDetails.isSuccessful) {
                movieDetails.body()?.let {
                    emit(ResponseApp.success(it))
                } ?: run {
                    emit(
                        ResponseApp.errorBackend<MovieDetailsResponse>(
                            movieDetails.code(),
                            movieDetails.errorBody()
                        )
                    )
                }
            } else {
                emit(
                    ResponseApp.errorBackend<MovieDetailsResponse>(
                        movieDetails.code(),
                        movieDetails.errorBody()
                    )
                )
            }
        } catch (e: Exception) {
            emit(ResponseApp.errorSystem(e))
        }
    }
}