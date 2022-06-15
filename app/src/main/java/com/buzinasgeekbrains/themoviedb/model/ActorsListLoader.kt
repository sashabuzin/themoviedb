package com.buzinasgeekbrains.themoviedb.model

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.buzinasgeekbrains.themoviedb.BuildConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object ActorsListLoader {
    fun load(listener: OnActorLoadListener) {
        Thread {
            var urlConnection: HttpsURLConnection? = null
            val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
            try {
                val uri = URL(
                            "https://api.themoviedb.org/3/person/popular?api_key=${BuildConfig.THEMOVIEDB_API_KEY}&language=en-US&page=1"
                )

                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 2000
                urlConnection.connectTimeout = 2000
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = reader.lines().collect(Collectors.joining("\n"))

                val itemType = object: TypeToken<PopularActorsListDTO>() {}.type
                val popularActorsListDTO = Gson().fromJson<PopularActorsListDTO>(result, itemType)
                handler.post { listener.onLoaded(popularActorsListDTO) }

            } catch (e: Exception) {
                handler.post {
                    listener.onFailed(e)
                }

            } finally {
                urlConnection?.disconnect()
            }
        }.start()
    }

    interface OnActorLoadListener {
        fun onLoaded(popularActorsListDTO: PopularActorsListDTO)
        fun onFailed(throwable: Throwable)
    }
}