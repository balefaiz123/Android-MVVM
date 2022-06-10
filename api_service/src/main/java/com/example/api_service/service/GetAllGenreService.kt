package com.example.api_service.service

import com.example.api_service.Constant.API_KEY
import com.example.common.entity.genre.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetAllGenreService {

    @GET("genre/movie/list")
    suspend fun getAllGenre(
        @Query("language") language: String = "id-ID",
        @Query("api_key") api_key : String = API_KEY
    ) : Response<GenreResponse>

}