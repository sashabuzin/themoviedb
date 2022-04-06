package com.buzinasgeekbrains.themoviedb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PopularActorsListDTO (
    val page: Int,
    val results: List<ActorDTO>,
    val total_pages: Int,
    val total_results: Int
    )

@Parcelize
data class ActorDTO (
    val adult: Boolean?,
    val gender: Int?,
    val id: Int?,
//    val known_for: List<FilmedInDTO>?,
    val known_for_department: String?,
    val name: String?,
    val popularity: Double?,
    val profile_path: String?
): Parcelable

data class ActorDetailsDTO(
    val also_known_as: Array<String>?,
    val biography: String?,
    val birthday: String?,
    val deathday: String?,
    val gender: Int?,
    val homepage: String?,
    val id: Int?,
    val imdb_id: String?,
    val known_for_department: String?,
    val name: String?,
    val place_of_birth: String?,
    val popularity: Double?,
    val profile_path: String?,
)
