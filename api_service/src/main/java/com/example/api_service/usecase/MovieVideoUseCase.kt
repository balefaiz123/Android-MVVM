package com.example.api_service.usecase

import com.example.api_service.service.GetMovieTrailerService
import com.example.common.entity.ResponseApp
import kotlinx.coroutines.flow.flow
import com.example.common.entity.movie_trailer.MovieVideoResponse

class MovieVideoUseCase(val getMovieTrailerService: GetMovieTrailerService) {

    operator fun invoke(movieId : Int) =flow {
        try {
            emit(ResponseApp.loading())
            val movieTrailer = getMovieTrailerService.getMovieTrailer(movieId)
            if (movieTrailer.isSuccessful) {
                movieTrailer.body()?.let {
                    emit(ResponseApp.success(it))
                } ?: run {
                    emit(
                        ResponseApp.errorBackend<MovieVideoResponse>(
                            movieTrailer.code(),
                            movieTrailer.errorBody()
                        )
                    )
                }
            } else {
                emit(
                    com.example.common.entity.ResponseApp.errorBackend(
                        movieTrailer.code(),
                        movieTrailer.errorBody()
                    )
                )
            }
        } catch (e: Exception) {
            emit(ResponseApp.errorSystem(e))
        }
    }
}