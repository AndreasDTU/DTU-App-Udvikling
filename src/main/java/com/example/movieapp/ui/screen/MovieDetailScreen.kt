package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.data.model.Movie
import com.example.movieapp.viewmodels.MovieDetailViewModel

@Composable
fun MovieDetailScreen(movieId: String, viewModel: MovieDetailViewModel = viewModel()) {
    val movieDetails by viewModel.movieDetails.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        movieDetails?.let { movie ->
            MovieDetailContent(movie)
        } ?: errorMessage?.let { error ->
            Text(
                text = error,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MovieDetailContent(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterpath}"),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = movie.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Release Date: WORK IN PROGRESS", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Rating: WORK IN PROGRESS", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = movie.overview, fontSize = 14.sp)
    }
}
