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
         TODO()
    }

    class MyReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            Log.d("BROADCAST", "${intent.action.toString()}")
            val id = intent.getIntExtra(MOVIE_ID, 0)

        }
    }

}