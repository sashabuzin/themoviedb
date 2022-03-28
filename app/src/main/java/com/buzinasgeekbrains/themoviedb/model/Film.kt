package com.buzinasgeekbrains.themoviedb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Film(
    val id: Int, val name: String,
    val releaseDate: String, val genre: String,
    val budget: Int, val revenue: Int,
    val rating: Double, val overview: String
): Parcelable
