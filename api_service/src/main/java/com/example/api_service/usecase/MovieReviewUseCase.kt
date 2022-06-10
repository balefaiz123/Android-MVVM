package com.example.api_service.usecase

import com.example.api_service.paging.DiscoverMoviesPager
import com.example.api_service.paging.MovieReviewPager
import com.example.api_service.service.GetMovieDetailsService
import com.example.common.entity.ResponseApp
import com.example.common.entity.moviereview.MovieReviewResponse
import kotlinx.coroutines.flow.flow

class MovieReviewUseCase(val getMovieDetailsService: GetMovieDetailsService) {

    operator fun invoke(movieId : String) =
        MovieReviewPager.createPager(10,getMovieDetailsService,movieId).flow


}