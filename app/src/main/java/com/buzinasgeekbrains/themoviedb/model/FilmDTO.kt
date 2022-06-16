package com.buzinasgeekbrains.themoviedb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ListFilmDTO(
        val page: Int,
        val results: List<FilmDTO>,
        val total_pages: Int,
        val total_results: Int
)

@Parcelize
data class FilmDTO(
        val adult: Boolean?,
        val backdrop_path: String?,
        val genre_ids: List<Int>?,
        val id: Int?,
        val media_type: String?,
        val original_language: String?,
        val original_title: String?,
        val overview: String?,
        val poster_path: String?,
        val release_date: String?,
        val title: String?,
        val video: Boolean?,
        val vote_average: Double?,
        val vote_count: Int?
): Parcelable
