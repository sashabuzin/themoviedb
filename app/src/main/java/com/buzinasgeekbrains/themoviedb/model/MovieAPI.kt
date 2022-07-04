package com.buzinasgeekbrains.themoviedb.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieAPI {
    @GET("3/movie")
    fun getMovie(
        @Query("api_key") api_key: String,
        @Query("id") id: Int,
    ): Call<FilmDTO>

    @GET("3/movie")
    fun getListMovie(
        @Query("api_key") api_key: String,
        @Query("sections") sections: String,
        @Query("page") page: Int
    ): Call<ListFilmDTO>

    @GET("3/person")
    fun getActor(
        @Query("api_key") api_key: String,
        @Query("id") id: Int,
    ): Call<ActorDTO>

    @GET("3/person/popular")
    fun getPopularActorsList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<PopularActorsListDTO>
}