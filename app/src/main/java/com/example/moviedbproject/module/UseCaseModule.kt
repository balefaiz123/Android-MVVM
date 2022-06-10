package com.example.moviedbproject.module

import com.example.api_service.service.GetAllGenreService
import com.example.api_service.service.GetMovieByGenreService
import com.example.api_service.service.GetMovieDetailsService
import com.example.api_service.service.GetMovieTrailerService
import com.example.api_service.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideGenreUseCase(getAllGenreService: GetAllGenreService) = GenreUseCase(getAllGenreService)

    @Provides
    fun provideMovieByGenreUseCase(getMovieByGenreService: GetMovieByGenreService) = MovieByGenreUseCase(getMovieByGenreService)

    @Provides
    fun provideMovieDetailUseCase(getMovieDetailsService: GetMovieDetailsService) = GetMovieDetailsUseCase(getMovieDetailsService)

    @Provides
    fun provideMovieReviewUseCase(getMovieDetailsService: GetMovieDetailsService) = MovieReviewUseCase(getMovieDetailsService)

    @Provides
    fun provideMovieVideoUseCase(getMovieTrailerService: GetMovieTrailerService) = MovieVideoUseCase(getMovieTrailerService)
}