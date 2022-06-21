package com.buzinasgeekbrains.themoviedb.model

import android.util.Log
import com.buzinasgeekbrains.themoviedb.view.MainFragment
import java.util.*

class RepositoryFilmImpl: RepositoryFilm {

        override fun getFilmFromServer(id: Int):  FilmDTO {
            var movieFromServer: FilmDTO? = null
            FilmLoader.load(id, object: FilmLoader.OnFilmLoadListener {

                override fun onLoaded(movie: FilmDTO) {
                    movieFromServer = movie

                }

                override fun onFailed(e: Throwable) {
                    Log.e("DEBUGLOG", e.toString(), e)
                }

            })
            Thread.sleep(1000)
            Log.d("JSON5", movieFromServer.toString())

            return movieFromServer ?: FilmDTO(false, "", emptyList(), 0,
                0, "", "", "", "",
                0, "", "", "", false, 0.0, 0)
    }



    override fun getNowPlayingFilmsFromServer(): ListFilmDTO {

        var listFilms: ListFilmDTO? = null
        FilmsListLoader.load(object: FilmsListLoader.OnFilmLoadListener {

            override fun onLoaded(listFilmDTO: ListFilmDTO) {
                listFilms = listFilmDTO

            }

            override fun onFailed(e: Throwable) {
                Log.e("DEBUGLOG", e.toString(), e)
            }

        }, MainFragment.MovieSections.NOW_PLAYING)
        Thread.sleep(1000)

        return listFilms ?: ListFilmDTO(1, emptyList(), 1, 1)
    }

    override fun getPopularFilmsFromServer(): ListFilmDTO {
        var listFilms: ListFilmDTO? = null
        FilmsListLoader.load(object: FilmsListLoader.OnFilmLoadListener {

            override fun onLoaded(listFilmDTO: ListFilmDTO) {
                listFilms = listFilmDTO

            }

            override fun onFailed(e: Throwable) {
                Log.e("DEBUGLOG", e.toString(), e)
            }

        }, MainFragment.MovieSections.POPULAR)
        Thread.sleep(1000)

        return listFilms ?: ListFilmDTO(1, emptyList(), 1, 1)
    }

    override fun getTopRatedPlayingFilmsFromServer(): ListFilmDTO {
        var listFilms: ListFilmDTO? = null
        FilmsListLoader.load(object: FilmsListLoader.OnFilmLoadListener {

            override fun onLoaded(listFilmDTO: ListFilmDTO) {
                listFilms = listFilmDTO

            }

            override fun onFailed(e: Throwable) {
                Log.e("DEBUGLOG", e.toString(), e)
            }

        }, MainFragment.MovieSections.TOP_RATED)
        Thread.sleep(1000)

        return listFilms ?: ListFilmDTO(1, emptyList(), 1, 1)
    }

    override fun getUpcomingFilmsFromServer(): ListFilmDTO {
        var listFilms: ListFilmDTO? = null
        FilmsListLoader.load(object: FilmsListLoader.OnFilmLoadListener {

            override fun onLoaded(listFilmDTO: ListFilmDTO) {
                listFilms = listFilmDTO

            }

            override fun onFailed(e: Throwable) {
                Log.e("DEBUGLOG", e.toString(), e)
            }

        }, MainFragment.MovieSections.UPCOMING)
        Thread.sleep(1000)

        return listFilms ?: ListFilmDTO(1, emptyList(), 1, 1)
    }


}
