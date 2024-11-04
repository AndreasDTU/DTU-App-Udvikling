package com.example.movieapp.data.model

import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<Movie>,  // List of Movie objects
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)