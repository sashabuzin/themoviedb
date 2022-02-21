package com.buzinasgeekbrains.themoviedb.Repositories

interface Repository {
    fun getFilmFromServer() : Film
    fun getActorFromRepository() : Actor
}