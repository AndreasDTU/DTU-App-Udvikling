package com.example.movieapp.repositories

import android.util.Log
import com.example.movieapp.data.remote.ApiClient
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieResponse
import retrofit2.Response
import com.example.movieapp.utils.Constants.API_KEY
class MovieRepository {
    private val apiService = ApiClient.instance

    suspend fun getPopularMovies(): List<Movie> {
        val response = apiService.getPopularMovies(API_KEY)
        Log.d("MovieRepository", "Response Code: ${response.code()}")

        if (response.isSuccessful) {
            val body = response.body()
            Log.d("MovieRepository", "Response Body: $body") // Log the entire response body

            // Log the results to check poster paths
            body?.results?.forEach { movie ->
                Log.d("MovieRepository", "Movie Title: ${movie.title}, Poster Path: ${movie.posterpath}")
            }
            return body?.results ?: emptyList()
        } else {
            Log.e("MovieRepository", "Error fetching movies: ${response.errorBody()?.string()}")
            return emptyList()
        }
    }
    suspend fun getScaryMovies(): List<Movie> {
        val response = apiService.getScaryMovies(API_KEY)
        Log.d("MovieRepository", "Response Code: ${response.code()}")

        if (response.isSuccessful) {
            val body = response.body()
            Log.d("MovieRepository", "Response Body: $body") // Log the entire response body

            // Log the results to check poster paths
            body?.results?.forEach { movie ->
                Log.d("MovieRepository", "Movie Title: ${movie.title}, Poster Path: ${movie.posterpath}")
            }
            return body?.results ?: emptyList()
        } else {
            Log.e("MovieRepository", "Error fetching movies: ${response.errorBody()?.string()}")
            return emptyList()
        }
    }

    suspend fun getFunnyMovies(): List<Movie> {
        val response = apiService.getFunnyMovies(API_KEY)


        if (response.isSuccessful) {
            val body = response.body()
            Log.d("MovieRepository", "Response Body: $body") // Log the entire response body
            // Log the results to check poster paths
            body?.results?.forEach { movie ->
                Log.d("MovieRepository", "Movie Title: ${movie.title}, Poster Path: ${movie.posterpath}")
            }
            return body?.results ?: emptyList()
        } else {
            Log.e("MovieRepository", "Error fetching movies: ${response.errorBody()?.string()}")
            return emptyList()
        }
    }

    suspend fun getMovieDetails(movieId: String): Movie {
        val response = apiService.getMovieDetails(movieId.toInt(), API_KEY)
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            } ?: throw Exception("No movie details found")
        } else {
            throw Exception("Failed to load movie details: ${response.message()}")
        }
    }

}
