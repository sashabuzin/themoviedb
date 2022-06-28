package com.buzinasgeekbrains.themoviedb.model

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.buzinasgeekbrains.themoviedb.BuildConfig
import com.buzinasgeekbrains.themoviedb.view.MainFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object FilmsListLoader {
    fun load(listener: OnFilmLoadListener, movieSections: MainFragment.MovieSections) {
        Thread {
            val sections = when (movieSections) {
                MainFragment.MovieSections.NOW_PLAYING -> "now_playing"
                MainFragment.MovieSections.POPULAR -> "popular"
                MainFragment.MovieSections.TOP_RATED -> "top_rated"
                MainFragment.MovieSections.UPCOMING -> "upcoming"
            }
            var urlConnection: HttpsURLConnection? = null
            val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
            try {

                val uri = URL(
                    "https://api.themoviedb.org/3/movie/$sections?api_key=${BuildConfig.THEMOVIEDB_API_KEY}&language=en-US&page=1"

                )
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 1500
                urlConnection.connectTimeout = 1500
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))

                val result = reader.lines().collect(Collectors.joining("\n"))

                val itemType = object: TypeToken<ListFilmDTO>() {}.type
                val listFilmDTO = Gson().fromJson<ListFilmDTO>(result, itemType)
                handler.post { listener.onLoaded(listFilmDTO) }

            } catch (e: Exception) {
                handler.post {
                    listener.onFailed(e)
                }

            } finally {
                urlConnection?.disconnect()
            }
        }.start()
    }

    fun loadRetrofit(listener: OnFilmLoadListener, movieSections: MainFragment.MovieSections) {

    }

    interface OnFilmLoadListener {
        fun onLoaded(listFilmDTO: ListFilmDTO)
        fun onFailed(throwable: Throwable)
    }
}