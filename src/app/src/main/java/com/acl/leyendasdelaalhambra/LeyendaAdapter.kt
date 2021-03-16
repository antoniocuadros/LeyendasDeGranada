package com.acl.leyendasdelaalhambra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class LeyendaAdapter:ListAdapter<Leyenda, LeyendaAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeyendaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.leyenda_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeyendaAdapter.ViewHolder, position: Int) {
        val leyenda = getItem(position)
        holder.bind(pokemon)
    }


}