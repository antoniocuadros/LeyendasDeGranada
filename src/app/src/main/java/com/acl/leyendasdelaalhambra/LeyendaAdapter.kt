package com.acl.leyendasdelaalhambra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class LeyendaAdapter:ListAdapter<Leyenda, LeyendaAdapter.ViewHolder>(DiffCallback) {
    companion object DiffCallback:DiffUtil.ItemCallback<Leyenda>(){
        override fun areItemsTheSame(oldItem: Leyenda, newItem: Leyenda): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Leyenda, newItem: Leyenda): Boolean {
            return oldItem == newItem
        }
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val nombre_leyenda = view.findViewById<TextView>(R.id.leyenda_nombre)
        //Falta el tema de la imagen

        fun bind(leyenda:Leyenda){
            nombre_leyenda.text = leyenda.nombre
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeyendaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.leyenda_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeyendaAdapter.ViewHolder, position: Int) {
        val leyenda = getItem(position)
        holder.bind(leyenda)
    }


}