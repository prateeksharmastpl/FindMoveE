package com.mrprateek.findmovee.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrprateek.findmovee.data.repository.MovieRepository
import com.mrprateek.findmovee.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        viewModelScope.launch {
            try {
                val response = repository.fetchPopularMovies()
                _movies.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}