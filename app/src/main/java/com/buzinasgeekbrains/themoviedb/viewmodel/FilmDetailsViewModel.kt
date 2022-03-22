package com.buzinasgeekbrains.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzinasgeekbrains.themoviedb.model.Film
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilm
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilmImpl

class FilmDetailsViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: RepositoryFilm = RepositoryFilmImpl()


    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getFilmFromRemoteSource() {

    }

    fun getFilmFromLocalStorage() {
        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(500)
            val film = repository.getFilmFromServer()
            liveDataToObserve.postValue(AppState.Success(film.firstOrNull()))

        }.start()
    }

}