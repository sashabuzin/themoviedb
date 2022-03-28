package com.buzinasgeekbrains.themoviedb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.model.Film

class FilmsFragmentAdapter (private var onItemViewClickListener:
                            OnItemViewClickListener?) :
    RecyclerView.Adapter<FilmsFragmentAdapter.MainViewHolder>() {

    private var filmsData: List<*> = listOf<List<*>>()

    fun setFilm(data: List<*>) {
        filmsData = data
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
        holder.bind(filmsData[position] as Film)
    }
    override fun getItemCount(): Int = filmsData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(film: Film) {
            itemView.apply {
                findViewById<TextView>(R.id.film_name_card_text_view).text =film.name
                setOnClickListener { onItemViewClickListener?.onItemViewClick(film) }
            }
        }
    }
    fun removeListener() {
        onItemViewClickListener = null
    }

    fun interface OnItemViewClickListener {
        fun onItemViewClick(film: Film)
    }

}