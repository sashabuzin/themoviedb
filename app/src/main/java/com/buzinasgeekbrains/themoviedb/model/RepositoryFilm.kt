package com.buzinasgeekbrains.themoviedb.model

interface RepositoryFilm {
    fun getFilmFromServer(): List<Film>
    fun getFilmFromLocalStorage(): List<Film>
}

interface RepositoryActor {
    fun getActorFromServer(): List<Actor>
    fun getActorFromLocalStorage(): List<Actor>
}