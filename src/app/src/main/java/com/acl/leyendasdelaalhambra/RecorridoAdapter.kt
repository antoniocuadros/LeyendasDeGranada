package com.acl.leyendasdelaalhambra

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.recorrido_item.view.*

class RecorridoAdapter(var listaRecorridos:MutableList<Recorrido>): BaseAdapter(){

    //Se va a llamar cuando se vaya a mostrar la lista de items
    //Construye cada elemento
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val recorrido = this.listaRecorridos[position]
        val vista:View = View.inflate(parent?.context, R.layout.recorrido_item, null)

        val imagen_recorrido = vista.findViewById<ImageView>(R.id.imagen_recorrido)
        val texto_recorrido = vista.findViewById<TextView>(R.id.texto_recorrido)

        imagen_recorrido.setImageResource(R.drawable.leones)
        texto_recorrido.text = recorrido.nombre

        return vista
    }

    //Para cada posición obtendrá la información de cada item
    override fun getItem(position: Int): Any {
        return listaRecorridos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listaRecorridos.size
    }
}