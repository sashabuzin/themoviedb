package com.buzinasgeekbrains.themoviedb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.model.Actor

class ActorsFragmentAdapter(private var onItemViewClickListener:
                            OnItemViewClickListener?) :
    RecyclerView.Adapter<ActorsFragmentAdapter.MainViewHolder>() {

    private var actorData: List<*> = listOf<List<*>>()

    fun setActor(data: List<*>) {
        actorData = data
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
        holder.bind(actorData[position] as Actor)
    }
    override fun getItemCount(): Int = actorData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(actor: Actor) {
            itemView.apply {
                findViewById<TextView>(R.id.actor_name_card_text_view).text =
                    actor.name
                setOnClickListener { onItemViewClickListener?.onItemViewClick(actor) }
            }
        }
    }
    fun removeListener() {
        onItemViewClickListener = null
    }

    fun interface OnItemViewClickListener {
        fun onItemViewClick(actor: Actor)
    }
}