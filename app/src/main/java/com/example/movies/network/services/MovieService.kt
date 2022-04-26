package com.example.movies.network.services

import com.example.movies.model.MovieDesc
import com.example.movies.model.TrendingMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieService {

    @GET("/3/trending/movie/week")
    suspend fun getPopularMovies(): Response<TrendingMovieResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int): Response<MovieDesc>
}