package com.example.api_service.service

import com.example.api_service.Constant.API_KEY
import com.example.common.entity.movie_discover.MovieDiscoverResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMovieByGenreService {

    @GET("discover/movie")
    suspend fun getMovieDiscoverByGenre(
        @Query("with_genres") genres : String,
        @Query("page") page : Int,
        @Query("api_key") api_key : String = API_KEY
    ) : Response<MovieDiscoverResponse>


}