package com.buzinasgeekbrains.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzinasgeekbrains.themoviedb.model.RepositoryActor
import com.buzinasgeekbrains.themoviedb.model.RepositoryActorImpl
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilm
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilmImpl

class ActorDetailsViewModel : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: RepositoryActor = RepositoryActorImpl()


    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getActorFromServer() {
        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(500)
            val actor = repository.getActorFromServer()
            liveDataToObserve.postValue(AppState.Success(actor))

        }.start()
    }
}