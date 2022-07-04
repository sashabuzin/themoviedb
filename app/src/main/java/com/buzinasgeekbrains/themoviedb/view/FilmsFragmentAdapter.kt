package com.buzinasgeekbrains.themoviedb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.model.Film
import com.buzinasgeekbrains.themoviedb.model.FilmDTO
import com.buzinasgeekbrains.themoviedb.model.ListFilmDTO

class FilmsFragmentAdapter (private var onItemViewClickListener:
                            OnItemViewClickListener?) :
    RecyclerView.Adapter<FilmsFragmentAdapter.MainViewHolder>() {

    private var filmsData: List<FilmDTO>? = null

    fun setFilm(data: ListFilmDTO) {
        filmsData = data.results
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.film_card, parent, false) as
                    View
        )
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filmsData?.get(position) as FilmDTO)
    }
    override fun getItemCount(): Int = filmsData?.size ?: 0

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(filmDTO: FilmDTO) {
            itemView.apply {
                findViewById<ImageView>(R.id.film_image_card_image_view).load("https://image.tmdb.org/t/p/w342${filmDTO.poster_path}")
                findViewById<TextView>(R.id.film_name_card_text_view).text =filmDTO.title
                setOnClickListener { onItemViewClickListener?.onItemViewClick(filmDTO) }
            }
        }
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    fun interface OnItemViewClickListener {
        fun onItemViewClick(filmDTO: FilmDTO)
    }

}