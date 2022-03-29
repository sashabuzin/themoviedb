package com.buzinasgeekbrains.themoviedb.model

import com.google.gson.annotations.SerializedName

data class ActorDTO(
    val also_known_as: Array<String>,
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
