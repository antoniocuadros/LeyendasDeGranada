package com.acl.leyendasdelaalhambra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class LeyendaAdapter(var listaLeyendas:MutableList<Leyenda>): BaseAdapter(){

    //Se va a llamar cuando se vaya a mostrar la lista de items
    //Construye cada elemento
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val leyenda = this.listaLeyendas[position]
        val vista:View = View.inflate(parent?.context, R.layout.leyenda_item, null)

        val imagen_leyenda = vista.findViewById<ImageView>(R.id.leyenda_imagen)
        val nombre_leyenda = vista.findViewById<TextView>(R.id.leyenda_nombre)

        imagen_leyenda.setImageResource(R.drawable.leones)
        Glide.with(vista).load(leyenda.imagen).into(imagen_leyenda);
        nombre_leyenda.text = leyenda.nombre

        return vista
    }

    //Para cada posición obtendrá la información de cada item
    override fun getItem(position: Int): Any {
        return listaLeyendas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listaLeyendas.size
    }
}