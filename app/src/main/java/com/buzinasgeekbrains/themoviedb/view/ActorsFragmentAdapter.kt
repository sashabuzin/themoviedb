package com.buzinasgeekbrains.themoviedb.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.model.ActorDTO
import com.buzinasgeekbrains.themoviedb.model.PopularActorsListDTO

class ActorsFragmentAdapter(private var onItemViewClickListener:
                            OnItemViewClickListener?) :
    RecyclerView.Adapter<ActorsFragmentAdapter.MainViewHolder>() {

    private var actorData: List<ActorDTO>? = null

    fun setActor(data: PopularActorsListDTO) {
        actorData = data.results

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.actor_card, parent, false) as
                    View
        )
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(actorData?.get(position) as ActorDTO)
    }
    override fun getItemCount(): Int = actorData?.size ?: 0

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(actorDTO: ActorDTO) {
            itemView.apply {
                findViewById<TextView>(R.id.actor_name_card_text_view).text =
                    actorDTO.name
                setOnClickListener { onItemViewClickListener?.onItemViewClick(actorDTO) }
            }
        }
    }
    fun removeListener() {
        onItemViewClickListener = null
    }

    fun interface OnItemViewClickListener {
        fun onItemViewClick(actorDTO: ActorDTO)
    }
}