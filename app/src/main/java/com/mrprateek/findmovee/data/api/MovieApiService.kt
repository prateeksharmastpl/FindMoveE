package com.mrprateek.findmovee.data.api

import com.mrprateek.findmovee.domain.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") bearerToken: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTFlMTUxOTU5MDgyYTI4ZTQxYmE4MmMzNTBjY2ZiMiIsIm5iZiI6MTc1MzI0MDA1OS4wOTYsInN1YiI6IjY4ODA1MWZiZjU2Yjg5YzQ5YjUyOTk3OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Y7lj_P2BQ4i2GDlCBIcrI-lJv2ohiBl7tRRwOgiAHNE",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse
}
