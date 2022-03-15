package com.buzinasgeekbrains.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzinasgeekbrains.themoviedb.model.RepositoryActor
import com.buzinasgeekbrains.themoviedb.model.RepositoryActorImpl
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilm
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilmImpl

class ActorsViewModel: ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: RepositoryActor = RepositoryActorImpl()


    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getActorFromRemoteSource() {

    }

    fun getActorFromLocalStorage() {
        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(1000)
            val film = repository.getActorFromLocalStorage()
            liveDataToObserve.postValue(AppState.Success(film[0]))

        }.start()
    }
}