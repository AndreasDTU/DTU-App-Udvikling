package com.example.movieapp.data.remote

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>



    @GET("discover/movie")
    suspend fun getScaryMovies(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int = 27, // Genre ID for Horror
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>


    @GET("discover/movie")
    suspend fun getFunnyMovies(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int = 35,  // Genre ID for comedy
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>


    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<Movie>
}