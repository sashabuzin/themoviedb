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

object ActorLoader {
    fun load(actorDTO: ActorDTO, listener: OnActorLoadListener) {
        Thread {
        var urlConnection: HttpsURLConnection? = null
        val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
        try {
            val uri = URL(
                "https://api.themoviedb.org/3/person/${actorDTO.id}?api_key=" +
                        "${BuildConfig.THEMOVIEDB_API_KEY}&language=en-US"
            )

            urlConnection = uri.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout = 1000
            urlConnection.connectTimeout = 1000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val result = reader.lines().collect(Collectors.joining("\n"))


            val actorDTO = Gson().fromJson(result, ActorDetailsDTO::class.java)
            handler.post { listener.onLoaded(actorDTO) }

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

    fun loadRetrofit(actorDTO: ActorDTO, listener: OnActorLoadListener) {

    }

    interface OnActorLoadListener {
        fun onLoaded(actorDetailsDTO: ActorDetailsDTO)
        fun onFailed(throwable: Throwable)
    }
}