package com.example.api_service.service

import com.example.api_service.Constant
import com.example.api_service.Constant.API_KEY
import com.example.common.entity.movie_detail.MovieDetailsResponse
import com.example.common.entity.moviereview.MovieReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieDetailsService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId : String,
        @Query("api_key") api_key : String = API_KEY
    ) : Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: String,
        @Query("page") page : Int,
        @Query("api_key") api_key: String = API_KEY
    ) : Response<MovieReviewResponse>
}