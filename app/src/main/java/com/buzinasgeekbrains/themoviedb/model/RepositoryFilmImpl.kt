package com.buzinasgeekbrains.themoviedb.model

import java.util.*

class RepositoryFilmImpl: RepositoryFilm {
        override fun getFilmFromServer(): List<Film> {
        return listOf(Film(1, "Terminator", Date(),
            "comedy", 1000, 1500, 5.0, "Best movie gsgsrgsgsrrgsrsgrgs"))

    }

    override fun getFilmFromLocalStorage(): List<Film> {
        return listOf(
            Film(2, "The matrix", Date(),"comedy", 2000, 3500, 4.7, "The matrix movie description"),
            Film(2, "The King's Man", Date(),"Action, Adventure, Thriller, War", 100000000, 124000000, 7.1, "The matrix movie description"),
            Film(2, "The matrix", Date(),"comedy", 2000, 3500, 6.2, "The matrix movie description"),
            Film(2, "The matrix", Date(),"comedy", 2000, 3500, 8.5, "The matrix movie description"),
            Film(2, "The matrix", Date(),"comedy", 2000, 3500, 9.3, "The matrix movie description"),


        )
    }


}
