package com.buzinasgeekbrains.themoviedb.model

interface RepositoryFilm {
    fun getFilmsFromServer(): List<Film>
    fun getFilmsFromLocalStorage(): List<Film>
    fun getNowPlayingFilmsFromLocalStorage(): List<Film>
    fun getPopularFilmsFromLocalStorage(): List<Film>
    fun getTopRatedPlayingFilmsFromLocalStorage(): List<Film>
    fun getUpcomingFilmsFromLocalStorage(): List<Film>
//    fun getFilmFromServer(): Film
}

