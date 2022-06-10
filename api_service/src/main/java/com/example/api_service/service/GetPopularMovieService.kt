package com.example.api_service.service

import com.example.api_service.Constant.API_KEY
import com.example.common.entity.popular_movie.PopularMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetPopularMovieService {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key : String = API_KEY
    ) : Response<PopularMovieResponse>

}