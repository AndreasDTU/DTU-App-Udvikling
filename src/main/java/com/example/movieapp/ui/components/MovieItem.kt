package com.example.movieapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.data.model.Movie

@Composable
fun MovieItem(movie: Movie, navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(120.dp) // Set a fixed width for each movie item
            .padding(8.dp)
            .clickable {
                navController.navigate("movie_detail/$(movie.id}")
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            // Load the movie poster image
            val painter: Painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterpath}")
            Image(
                painter = painter,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp) // Set the height for the poster
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 1 // Limit to one line for the title
            )
        }
    }
}

