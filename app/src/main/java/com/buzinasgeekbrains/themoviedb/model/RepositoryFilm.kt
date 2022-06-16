package com.buzinasgeekbrains.themoviedb.model

interface RepositoryFilm {
    fun getFilmsFromServer(): List<Film>
    fun getNowPlayingFilmsFromServer(): ListFilmDTO
    fun getPopularFilmsFromServer(): ListFilmDTO
    fun getTopRatedPlayingFilmsFromServer(): ListFilmDTO
    fun getUpcomingFilmsFromServer(): ListFilmDTO
}

