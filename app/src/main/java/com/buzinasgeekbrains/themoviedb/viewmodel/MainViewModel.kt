package com.buzinasgeekbrains.themoviedb.viewmodel

import androidx.lifecycle.*
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilm
import com.buzinasgeekbrains.themoviedb.model.RepositoryFilmImpl

class MainViewModel : ViewModel() {

    private val liveDataToObserveNowPlaying: MutableLiveData<AppState> = MutableLiveData()
    private val liveDataToObservePopular: MutableLiveData<AppState> = MutableLiveData()
    private val liveDataToObserveTopRated: MutableLiveData<AppState> = MutableLiveData()
    private val liveDataToObserveUpcoming: MutableLiveData<AppState> = MutableLiveData()
    private val repository: RepositoryFilm = RepositoryFilmImpl()

    //    private val mediatorLiveData: MediatorLiveData<AppState> = MediatorLiveData()

    fun getNowPlayingData(): LiveData<AppState> = liveDataToObserveNowPlaying
    fun getPopularData(): LiveData<AppState> = liveDataToObservePopular
    fun getTopRatedData(): LiveData<AppState> = liveDataToObserveTopRated
    fun getUpcomingData(): LiveData<AppState> = liveDataToObserveUpcoming

//    fun getData(): MediatorLiveData<AppState> = mediatorLiveData

    fun getFilmsFromRemoteSource() {

    }

//    private fun addToMediatorLiveData() {
//        mediatorLiveData.addSource(liveDataToObserveNowPlaying, Observer{
//            liveDataToObserveNowPlaying.postValue(it)
//        })
//        mediatorLiveData.addSource(liveDataToObservePopular, Observer {
//            liveDataToObservePopular.postValue(it)
//        })
//        mediatorLiveData.addSource(liveDataToObserveTopRated, Observer{
//            liveDataToObserveTopRated.postValue(it)
//        })
//        mediatorLiveData.addSource(liveDataToObserveUpcoming, Observer {
//            liveDataToObserveUpcoming.postValue(it)
//        })
//    }

    fun getFilmsFromLocalStorage() {
//        addToMediatorLiveData()
        liveDataToObserveNowPlaying.postValue(AppState.Loading)
        liveDataToObservePopular.postValue(AppState.Loading)
        liveDataToObserveTopRated.postValue(AppState.Loading)
        liveDataToObserveUpcoming.postValue(AppState.Loading)

        Thread {
            Thread.sleep(150)
            val filmNowPlaying = repository.getNowPlayingFilmsFromLocalStorage()
            liveDataToObserveNowPlaying.postValue(AppState.Success(filmNowPlaying))
            val filmPopular = repository.getPopularFilmsFromLocalStorage()
            liveDataToObservePopular.postValue(AppState.Success(filmPopular))
            val filmTopRated = repository.getTopRatedPlayingFilmsFromLocalStorage()
            liveDataToObserveTopRated.postValue(AppState.Success(filmTopRated))
            val filmUpcoming = repository.getUpcomingFilmsFromLocalStorage()
            liveDataToObserveUpcoming.postValue(AppState.Success(filmUpcoming))

        }.start()
    }
}