package com.acl.leyendasdelaalhambra

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recorrido_item.view.*
import java.util.*

/*
Esta clase representa el adaptador de la lista de recorridos. Este adaptador se encargará
de rellenar la vista de la lista de recorridos adaptando cada recorrido a un item según el
layout "recorrido_item" en forma de carta e irá añadiendo a la vista dichas cartas a la vista
que contiene la lista de recorridos. Por ello recibe en el constructor la lista de recorridos.
 */
class RecorridoAdapter(var listaRecorridos:MutableList<Recorrido>): BaseAdapter(){

    /*
    Este método se encarga de, para cada recorrido, rellenar la vista del item descrita en el layout
    "recorrido_item". Para ello se siguen los siguientes pasos:
        -> 1) Se obtiene la vista.
        -> 2) Se obtiene de la lista de recorridos la que corresponde a la posición que se va a construir
              obteniendo la posición como un parámetro.
        -> 3) Se definen variables que referiencian los elementos (views) que forman un item (carta)
              de la lista de recorridos.
        -> 4) Se asignan los valores del recorrido que va en esa posición a las vistas obtenidas en
              el paso anterior.
        -> 5) Se devuelve la vista
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Paso 1)
        val vista:View = View.inflate(parent?.context, R.layout.recorrido_item, null)

        // Paso 2)
        val recorrido = this.listaRecorridos[position]

        //Paso 3)
        val imagen_recorrido = vista.findViewById<ImageView>(R.id.imagen_recorrido)
        val texto_recorrido = vista.findViewById<TextView>(R.id.texto_recorrido)
        val texto_paradas = vista.findViewById<TextView>(R.id.texto_paradas)

        // Paso 4)
        imagen_recorrido.setImageResource(R.drawable.leones)
        Glide.with(vista).load(recorrido.imagen).into(imagen_recorrido);
        texto_recorrido.text = recorrido.nombre
        if(Locale.getDefault().getLanguage() == "es") {
            texto_paradas.text = "Paradas: " + recorrido.leyendas.size
        }
        else{
            texto_paradas.text = "Points of interest: " + recorrido.leyendas.size
        }

        // Paso 5
        return vista
    }

    /*
    Para una determinada posición dada como argumento se devuelve
    el recorrido asociado a dicha posición de la lista de recorridos.
     */
    override fun getItem(position: Int): Any {
        return listaRecorridos[position]
    }

    /*
    En este método, dada una posición como parámetro se devuelve un identificador,
    en este caso al ser una lista, es la misma posición.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /*
    Este método devuelve la longitud de la lista de recorridos.
     */
    override fun getCount(): Int {
        return listaRecorridos.size
    }
}