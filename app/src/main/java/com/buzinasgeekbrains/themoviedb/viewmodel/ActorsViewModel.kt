package com.buzinasgeekbrains.themoviedb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzinasgeekbrains.themoviedb.model.RepositoryActor
import com.buzinasgeekbrains.themoviedb.model.RepositoryActorImpl

class ActorsViewModel: ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: RepositoryActor = RepositoryActorImpl()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getPopularActorsFromServer() {
        liveDataToObserve.value = AppState.Loading

        Thread {
            val actors = repository.retrofitGetPopularActorsFromServer()
            liveDataToObserve.postValue(AppState.Success(actors))

        }.start()
    }
}