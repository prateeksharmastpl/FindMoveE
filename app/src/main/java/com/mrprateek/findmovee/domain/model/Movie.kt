package com.mrprateek.findmovee.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val overview: String,
    val release_date: String
)
