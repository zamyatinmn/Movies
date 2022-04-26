package com.example.movies.network.services

import com.example.movies.model.MovieDesc
import com.example.movies.model.TrendingMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("/3/trending/movie/week")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): Response<TrendingMovieResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int): Response<MovieDesc>
}