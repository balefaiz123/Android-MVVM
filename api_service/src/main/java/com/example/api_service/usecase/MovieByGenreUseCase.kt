package com.example.api_service.usecase

import com.example.api_service.paging.DiscoverMoviesPager
import com.example.api_service.service.GetMovieByGenreService
import com.example.common.entity.ResponseApp
import com.example.common.entity.movie_discover.MovieDiscoverResponse
import kotlinx.coroutines.flow.flow

class MovieByGenreUseCase(val movieByGenreService: GetMovieByGenreService) {

    operator fun invoke(genre : String) =
        DiscoverMoviesPager.createPager(10, movieByGenreService, genre).flow

}