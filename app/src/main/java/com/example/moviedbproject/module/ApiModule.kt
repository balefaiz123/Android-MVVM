package com.example.moviedbproject.module

import android.content.Context
import com.example.api_service.RetrofitClient
import com.example.api_service.service.GetAllGenreService
import com.example.api_service.service.GetMovieByGenreService
import com.example.api_service.service.GetMovieDetailsService
import com.example.api_service.service.GetMovieTrailerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideGenreService(retrofit: Retrofit) = retrofit.create(GetAllGenreService::class.java)

    @Provides
    @Singleton
    fun provideMovieByGenreService(retrofit: Retrofit) = retrofit.create(GetMovieByGenreService::class.java)

    @Provides
    @Singleton
    fun provideMovieDetailService(retrofit: Retrofit) = retrofit.create(GetMovieDetailsService::class.java)

    @Provides
    @Singleton
    fun provideMovieVideoService(retrofit: Retrofit) = retrofit.create(GetMovieTrailerService::class.java)
}