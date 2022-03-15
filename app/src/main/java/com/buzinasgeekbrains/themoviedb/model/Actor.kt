package com.buzinasgeekbrains.themoviedb.model

import java.util.*

data class Actor (
    val id: Int, val name: String, val birthday: String, val gender: Int, val placeOfBirth: String, val popularity: Double, val biography: String
)