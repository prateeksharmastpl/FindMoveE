package com.mrprateek.findmovee.domain.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
)
