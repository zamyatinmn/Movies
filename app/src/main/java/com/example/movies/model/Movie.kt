package com.example.movies.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @NonNull
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val poster_path: String,
    val genre_ids: List<Int>,
    val release_date: String
)