package com.buzinasgeekbrains.themoviedb.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Actor (
    val id: Int, val name: String, val birthday: String, val gender: Int, val placeOfBirth: String, val popularity: Double, val biography: String
): Parcelable
