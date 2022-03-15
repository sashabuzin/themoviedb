package com.buzinasgeekbrains.themoviedb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState ?: replaceMainContainer(FilmDetailsFragment.newInstance())


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
}