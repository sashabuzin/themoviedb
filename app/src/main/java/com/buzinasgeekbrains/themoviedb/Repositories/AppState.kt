package com.buzinasgeekbrains.themoviedb.Repositories

sealed class AppState {
    data class Success(val film: Film): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
