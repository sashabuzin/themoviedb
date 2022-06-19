package com.buzinasgeekbrains.themoviedb.model

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.buzinasgeekbrains.themoviedb.view.DETAILS_INTENT_FILTER
import com.buzinasgeekbrains.themoviedb.viewmodel.FilmDetailsViewModel

const val MOVIE_ID: String = "MOVIE_ID"

class FilmLoaderService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("SERVICE", intent?.getIntExtra(MOVIE_ID, 0).toString())
        sendBroadcast(intent, DETAILS_INTENT_FILTER)
        return super.onStartCommand(intent, flags, startId)

    }
}