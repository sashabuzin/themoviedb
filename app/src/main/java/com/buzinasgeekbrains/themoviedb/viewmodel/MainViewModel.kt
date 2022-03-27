package com.buzinasgeekbrains.themoviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilm
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilmImpl

class MainViewModel : ViewModel() {

    private val liveDataToObserveNowPlaying: MutableLiveData<AppState> = MutableLiveData()
    private val liveDataToObservePopular: MutableLiveData<AppState> = MutableLiveData()
    private val liveDataToObserveTopRated: MutableLiveData<AppState> = MutableLiveData()
    private val liveDataToObserveUpcoming: MutableLiveData<AppState> = MutableLiveData()
    private val mediatorLiveData: MediatorLiveData<AppState> = MediatorLiveData()
    private val repository: RepositoryFilm = RepositoryFilmImpl()



//    fun getData(): LiveData<AppState> = mediatorLiveData
    fun getData(): MediatorLiveData<AppState> = mediatorLiveData

    fun getFilmsFromRemoteSource() {

    }

    private fun addToMediatorLiveData() {
        mediatorLiveData.addSource(liveDataToObserveNowPlaying, Observer<AppState> {
            mediatorLiveData.postValue(it)
        })
        mediatorLiveData.addSource(liveDataToObservePopular, Observer<AppState> {
            mediatorLiveData.postValue(it)
        })
        mediatorLiveData.addSource(liveDataToObserveTopRated, Observer<AppState> {
            mediatorLiveData.postValue(it)
        })
        mediatorLiveData.addSource(liveDataToObserveUpcoming, Observer<AppState> {
            mediatorLiveData.postValue(it)
        })
    }

    fun getFilmsFromLocalStorage() {
        addToMediatorLiveData()
        mediatorLiveData.value = AppState.Loading

        Thread {
            Thread.sleep(500)
            val filmNowPlaying = repository.getNowPlayingFilmsFromLocalStorage()
            liveDataToObserveNowPlaying.postValue(AppState.Success(filmNowPlaying))
            val filmPopular = repository.getNowPlayingFilmsFromLocalStorage()
            liveDataToObservePopular.postValue(AppState.Success(filmPopular))
            val filmTopRated = repository.getNowPlayingFilmsFromLocalStorage()
            liveDataToObserveTopRated.postValue(AppState.Success(filmTopRated))
            val filmUpcoming = repository.getNowPlayingFilmsFromLocalStorage()
            liveDataToObserveUpcoming.postValue(AppState.Success(filmUpcoming))


        }.start()
    }
}