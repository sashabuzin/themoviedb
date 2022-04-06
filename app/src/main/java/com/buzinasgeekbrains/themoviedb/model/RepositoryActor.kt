package com.buzinasgeekbrains.themoviedb.model

interface RepositoryActor {
    fun getPopularActorsFromServer(): PopularActorsListDTO
    fun getActorFromServer(): ActorDetailsDTO
    fun getActorsFromLocalStorage(): List<Actor>
}