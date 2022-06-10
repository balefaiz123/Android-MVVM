package com.example.common.entity.movie_discover

data class MovieDiscoverResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)