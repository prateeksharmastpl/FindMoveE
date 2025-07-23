package com.mrprateek.findmovee.data.repository

import com.mrprateek.findmovee.data.api.MovieApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: MovieApiService
) {
    suspend fun fetchPopularMovies() = apiService.getPopularMovies()
}
