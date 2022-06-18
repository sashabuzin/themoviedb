package com.buzinasgeekbrains.themoviedb.model

interface RepositoryFilm {
    fun getFilmFromServer(id: Int): FilmDTO
    fun getNowPlayingFilmsFromServer(): ListFilmDTO
    fun getPopularFilmsFromServer(): ListFilmDTO
    fun getTopRatedPlayingFilmsFromServer(): ListFilmDTO
    fun getUpcomingFilmsFromServer(): ListFilmDTO
}

