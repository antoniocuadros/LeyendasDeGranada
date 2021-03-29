package com.acl.leyendasdelaalhambra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SliderImagenesAdapter(var imagen:MutableList<String>):RecyclerView.Adapter<SliderImagenesAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val  imagen = itemView.findViewById<ImageView>(R.id.imagen_slider)
    }

    //Inflamos la vista de cada item
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): SliderImagenesAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_imagen_slider, parent, false))
    }

    override fun getItemCount(): Int {
        return imagen.size
    }

    override fun onBindViewHolder(holder: SliderImagenesAdapter.Pager2ViewHolder, position: Int) {
        //Glide.with(holder.itemView).load(imagen[position]).into(holder.imagen);
        holder.imagen.setImageResource(R.drawable.imagen_prueba)
    }

}