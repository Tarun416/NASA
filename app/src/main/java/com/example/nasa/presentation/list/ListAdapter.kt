package com.example.nasa.presentation.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.PlanetaryResponse
import com.example.nasa.R
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(private val context: Context, private val onPicClick: OnPicClick, private val planetaryResponse : MutableList<PlanetaryResponse> = mutableListOf() ) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return planetaryResponse.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       Glide.with(context).load(planetaryResponse[position].url).into(holder.picture)

        holder.picture.setOnClickListener{onPicClick.onClick(position)}
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val picture = itemView.img
    }

    fun update(response : List<PlanetaryResponse>)
    {
        planetaryResponse.clear()
        planetaryResponse.addAll(response)
        notifyDataSetChanged()
    }

    interface OnPicClick
    {
        fun onClick(pos : Int)
    }

}