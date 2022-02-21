package com.buzinasgeekbrains.themoviedb.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buzinasgeekbrains.themoviedb.Repositories.AppState
import com.buzinasgeekbrains.themoviedb.Repositories.Film
import java.util.*

class FilmDetailsViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()


    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getFilm() {
        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(1000)
            val film = Film(1, "Terminator", Date(),
                "comedy", 1000, 1500, 5.0, "Best movie gsgsrgsgsrrgsrsgrgs")
            liveDataToObserve.postValue(AppState.Success(film))

        }.start()
    }
}