package com.buzinasgeekbrains.themoviedb.viewmodel

import com.buzinasgeekbrains.themoviedb.model.Film

sealed class AppState {
    data class Success<T>(val data: T): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
