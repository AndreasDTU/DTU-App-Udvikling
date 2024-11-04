package com.example.movieapp.data.model

import com.squareup.moshi.Json

data class Movie(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterpath: String?,
    @Json(name = "vote_average") val voteaverage: Double,
    @Json(name = "release_date") val releasedate: String
)
