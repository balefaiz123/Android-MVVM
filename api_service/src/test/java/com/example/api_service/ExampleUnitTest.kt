package com.example.api_service

import com.example.api_service.service.GetAllGenreService
import com.example.api_service.service.GetMovieByGenreService
import com.example.api_service.service.GetPopularMovieService
import com.example.api_service.usecase.GenreUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testGenre(){
        runBlocking {
            val response = RetrofitClient.Client.create(GetAllGenreService::class.java).getAllGenre()
            println(response.body())
        }
    }

    @Test
    fun testGenreUseCase(){
        val client = RetrofitClient.Client
        val genreService = client.create(GetAllGenreService::class.java)
        val useCase = GenreUseCase(genreService)

        runBlocking {
            useCase.invoke().collect{
                print(it)
            }
        }
    }
}