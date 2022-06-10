package com.example.api_service.usecase

import com.example.api_service.service.GetAllGenreService
import com.example.common.entity.ResponseApp
import com.example.common.entity.genre.GenreResponse
import kotlinx.coroutines.flow.flow

class GenreUseCase(val getAllGenreService: GetAllGenreService) {

    operator fun invoke() = flow {
        try {
            emit(ResponseApp.loading())
            val movieGenre = getAllGenreService.getAllGenre()
            if (movieGenre.isSuccessful) {
                movieGenre.body()?.let {
                    emit(ResponseApp.success(it))
                } ?: run {
                    emit(
                        ResponseApp.errorBackend<GenreResponse>(
                            movieGenre.code(),
                            movieGenre.errorBody()
                        )
                    )
                }
            } else {
                emit(ResponseApp.errorBackend(movieGenre.code(), movieGenre.errorBody()))
            }
        } catch (e: Exception) {
            emit(ResponseApp.errorSystem(e))
        }
    }
}