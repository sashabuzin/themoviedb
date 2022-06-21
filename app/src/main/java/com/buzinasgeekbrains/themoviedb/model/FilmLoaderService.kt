package com.buzinasgeekbrains.themoviedb.model

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

const val MOVIE_ID: String = "MOVIE_ID"

class FilmLoaderService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val movieId: Int? = intent?.getIntExtra(MOVIE_ID, 0)
        val newIntent = Intent("com.buzinasgeekbrains.themoviedb.action").also {
            it.putExtra(MOVIE_ID, movieId)
        }
        sendBroadcast(newIntent)
        return super.onStartCommand(intent, flags, startId)

    }
}