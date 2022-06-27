package com.buzinasgeekbrains.themoviedb.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("3/movie")
    fun getMovie(
        @Query("id") token: Int,
    ): Call<FilmDTO>

    @GET("")
    fun getListMovie(

    ): Call<ListFilmDTO>

    @GET("")
    fun getActor(
        @Query("id") token: Int,
    ): Call<ActorDTO>
}