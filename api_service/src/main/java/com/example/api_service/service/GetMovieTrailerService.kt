package com.example.api_service.service

import com.example.api_service.Constant.API_KEY
import com.example.common.entity.movie_trailer.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieTrailerService {
    @GET("movie/{id}/videos")
    suspend fun getMovieTrailer(
        @Path("id") id:Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieVideoResponse>
}