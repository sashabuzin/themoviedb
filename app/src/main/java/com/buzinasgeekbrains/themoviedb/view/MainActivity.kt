package com.buzinasgeekbrains.themoviedb.view

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.broadcastReceiver.MainBroadcastReceiver
import com.buzinasgeekbrains.themoviedb.databinding.ActivityMainBinding
import com.buzinasgeekbrains.themoviedb.model.FilmLoaderService
import com.buzinasgeekbrains.themoviedb.viewmodel.FilmDetailsViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val receiver = MainBroadcastReceiver()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        savedInstanceState ?: replaceMainContainer(MainFragment.newInstance())
        binding.bottomToolbar.toolbarMainBtn.setOnClickListener{
            replaceMainContainer(MainFragment.newInstance())
        }
        binding.bottomToolbar.toolbarPeopleBtn.setOnClickListener{
            replaceMainContainer(ActorsFragment.newInstance())
        }
    }

    private fun replaceMainContainer(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, fragment)
            .commit()
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)

        super.onDestroy()
    }

}