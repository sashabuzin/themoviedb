package com.buzinasgeekbrains.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzinasgeekbrains.themoviedb.model.RepositoryActor
import com.buzinasgeekbrains.themoviedb.model.RepositoryActorImpl

class ActorsViewModel: ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: RepositoryActor = RepositoryActorImpl()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getActorsFromRemoteSource() {

    }

    fun getActorFromLocalStorage() {
        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(500)
            val actor = repository.getActorsFromLocalStorage()
            liveDataToObserve.postValue(AppState.Success(actor))

        }.start()
    }
}