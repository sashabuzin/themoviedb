package com.buzinasgeekbrains.themoviedb.viewmodel

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzinasgeekbrains.themoviedb.model.FilmLoaderService
import com.buzinasgeekbrains.themoviedb.model.MOVIE_ID
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilm
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilmImpl

class FilmDetailsViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: RepositoryFilm = RepositoryFilmImpl()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getFilmFromServer(id: Int) {
        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(500)
            val movieInfo = repository.getFilmFromServer(id)
            liveDataToObserve.postValue(AppState.Success(movieInfo))

        }.start()
    }

    inner class MyReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            getFilmFromServer(intent.getIntExtra(MOVIE_ID, 0))
        }
    }

}