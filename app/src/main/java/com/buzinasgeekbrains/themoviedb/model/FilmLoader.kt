package com.buzinasgeekbrains.themoviedb.model

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.buzinasgeekbrains.themoviedb.BuildConfig
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object FilmLoader {
    fun load(id: Int, listener: OnFilmLoadListener) {
        Thread {
            var urlConnection: HttpsURLConnection? = null
            val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
            try {
                val uri = URL(
                    "https://api.themoviedb.org/3/movie/$id?api_key=" +
                            "${BuildConfig.THEMOVIEDB_API_KEY}&language=en-US"
                )

                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 2000
                urlConnection.connectTimeout = 2000
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = reader.lines().collect(Collectors.joining("\n"))


                val filmDTO = Gson().fromJson(result, FilmDTO::class.java)
                handler.post { listener.onLoaded(filmDTO) }

            } catch (e: Exception) {
                handler.post {
                    listener.onFailed(e)
                }
                Log.e("DEBUGLOG", "FAIL CONNECTION", e)
            } finally {
                urlConnection?.disconnect()
            }
        }.start()
    }

    interface OnFilmLoadListener {
        fun onLoaded(filmDTO: FilmDTO)
        fun onFailed(throwable: Throwable)
    }
}