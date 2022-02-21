package com.buzinasgeekbrains.themoviedb.Repositories

import java.util.*

data class Film(
    val id: Int, val name: String,
    val date: Date, val genre: String,
    val budget: Int, val revenue: Int,
    val rating: Double, val description: String
)