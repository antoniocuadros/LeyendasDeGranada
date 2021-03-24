package com.acl.leyendasdelaalhambra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class LeyendaAdapter:ListAdapter<Leyenda, LeyendaAdapter.ViewHolder>(DiffCallback) {
    companion object DiffCallback:DiffUtil.ItemCallback<Leyenda>(){
        override fun areItemsTheSame(oldItem: Leyenda, newItem: Leyenda): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Leyenda, newItem: Leyenda): Boolean {
            return oldItem == newItem
        }
    }

    //Listener para cuando pulsemos una casilla
    lateinit var onItemClickListener: (Leyenda) -> Unit



    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val nombre_leyenda = view.findViewById<TextView>(R.id.leyenda_nombre)
        private val imagen_leyenda = view.findViewById<CircleImageView>(R.id.leyenda_imagen)
        //Falta el tema de la imagen

        fun bind(leyenda:Leyenda){
            nombre_leyenda.text = leyenda.nombre

            imagen_leyenda.setImageResource(R.drawable.leones)
            Glide.with(view).load(leyenda.imagen).into(imagen_leyenda);

            view.setOnClickListener{
                if(::onItemClickListener.isInitialized){
                    onItemClickListener(leyenda)
                }
            }
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