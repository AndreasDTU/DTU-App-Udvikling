package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.Movie
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> get() = _popularMovies

    private val _scaryMovies = MutableStateFlow<List<Movie>>(emptyList())
    val scaryMovies: StateFlow<List<Movie>> get() = _scaryMovies

    private val _funnyMovies = MutableStateFlow<List<Movie>>(emptyList())
    val funnyMovies: StateFlow<List<Movie>> get() = _funnyMovies

    init {
        fetchPopularMovies()
        fetchScaryMovies()
        fetchFunnyMovies()
    }

    val topMovie: StateFlow<Movie> = _popularMovies.map { movies ->
        // Return the first movie or a default one if the list is empty
        movies.firstOrNull() ?: Movie(0, "No Movie Available", "No description available", null, 0.0, "N/A")
    }.stateIn(viewModelScope, SharingStarted.Lazily, Movie(0, "No Movie Available", "No description available", null, 0.0, "N/A"))





    private fun fetchPopularMovies() {
        viewModelScope.launch {
            try {
                val movies = movieRepository.getPopularMovies()
                _popularMovies.value = movies
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun fetchScaryMovies() {
        viewModelScope.launch {
            try {
                val scaryMovies = movieRepository.getScaryMovies()
                _scaryMovies.value = scaryMovies
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun fetchFunnyMovies() {
        viewModelScope.launch {
            try {
                val funnyMovies = movieRepository.getFunnyMovies()
                _funnyMovies.value = funnyMovies
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
