package com.buzinasgeekbrains.themoviedb.model

interface RepositoryActor {
    fun getActorsFromServer(): List<Actor>
    fun getActorsFromLocalStorage(): List<Actor>
}