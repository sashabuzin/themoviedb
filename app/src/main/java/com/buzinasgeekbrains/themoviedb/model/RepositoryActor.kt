package com.buzinasgeekbrains.themoviedb.model

interface RepositoryActor {
    fun getPopularActorsFromServer(): PopularActorsListDTO
    fun getActorFromServer(): ActorDetailsDTO
    fun retrofitGetPopularActorsFromServer(): PopularActorsListDTO
    fun retrofitGetActorFromServer(): ActorDetailsDTO
    fun getActorsFromLocalStorage(): List<Actor>
}