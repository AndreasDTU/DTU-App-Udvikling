package com.example.movieapp.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieapp.ui.screen.MainScreen
import com.example.movieapp.viewmodels.MovieViewModel
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.ui.screens.MovieDetailScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = MovieViewModel(MovieRepository())
            val topMovie = viewModel.topMovie.collectAsState().value
            val popularMovies = viewModel.popularMovies.collectAsState().value
            val scaryMovies = viewModel.scaryMovies.collectAsState().value
            val funnyMovies = viewModel.funnyMovies.collectAsState().value
            NavHost(navController, startDestination = "main") {
                composable("main") {
                    MainScreen(
                        navController = navController,
                        trendingMovies = popularMovies,
                        scaryMovies = scaryMovies,
                        funnyMovies = funnyMovies,
                        topMovie = topMovie
                    )
                }
                composable("movieDetail/{movieId}") { backStackEntry ->
                    val movieId = backStackEntry.arguments?.getString("movieId")
                    MovieDetailScreen(movieId.toString())
                }
            }

        }
    }
}
