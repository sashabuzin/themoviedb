package com.buzinasgeekbrains.themoviedb.model

import android.os.Handler
import android.os.Looper
import com.buzinasgeekbrains.themoviedb.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection
import kotlin.Exception

object ActorsListLoader {

    private val movieAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(MovieAPI::class.java)

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
                urlConnection.readTimeout = 1500
                urlConnection.connectTimeout = 1500
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

    fun loadRetrofit(listener: OnActorLoadListener) {
        movieAPI.getPopularActorsList(BuildConfig.THEMOVIEDB_API_KEY, 1)
            .enqueue(object: Callback<PopularActorsListDTO> {

                override fun onResponse(call: Call<PopularActorsListDTO>, response: Response<PopularActorsListDTO>) {
                    if (response.isSuccessful) {
                        response.body()?.let { listener.onLoaded(it) }
                    } else {
                        listener.onFailed(Exception(response.message()))
                    }
                }

                override fun onFailure(call: Call<PopularActorsListDTO>, t: Throwable) {
                    listener.onFailed(t)
                }
            })
    }

    interface OnActorLoadListener {
        fun onLoaded(popularActorsListDTO: PopularActorsListDTO)
        fun onFailed(throwable: Throwable)
    }
}