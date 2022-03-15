package com.buzinasgeekbrains.themoviedb.model

import android.os.Parcelable
import java.util.*

data class Film(
    val id: Int, val name: String,
    val date: Date, val genre: String,
    val budget: Int, val revenue: Int,
    val rating: Double, val description: String

)

fun getDefaultFilm(): Film {
    return Film(2, "The matrix", Date(),
        "comedy", 2000, 3500, 4.7, "The matrix movie description")
}