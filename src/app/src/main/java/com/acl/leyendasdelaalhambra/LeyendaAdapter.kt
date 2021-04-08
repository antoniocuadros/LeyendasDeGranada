package com.acl.leyendasdelaalhambra

import android.content.res.Resources
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

/*
Esta clase representa el adaptador de la lista de leyendas. Este adaptador se encargará
de rellenar la vista de la lista de leyendas adaptando cada leyenda a un item según el
layout "leyenda_item" en forma de carta e irá añadiendo a la vista dichas cartas a la vista
que contiene la lista de leyendas. Por ello recibe en el constructor la lista de leyendas.
 */
class LeyendaAdapter(var listaLeyendas:MutableList<Leyenda>): BaseAdapter(){

    //Se va a llamar cuando se vaya a mostrar la lista de items
    //Construye cada elemento
    /*
    Este método se encarga de, para cada leyenda, rellenar la vista del item descrita en el layout
    "leyenda_item". Para ello se siguen los siguientes pasos:
        -> 1) Se obtiene la vista.
        -> 2) Se obtiene de la lista de leyendas la que corresponde a la posición que se va a construir
              obteniendo la posición como un parámetro.
        -> 3) Se definen variables que referiencian los elementos (views) que forman un item (carta)
              de la lista de leyendas.
        -> 4) Se asignan los valores de la leyenda que va en esa posición a las vistas obtenidas en
              el paso anterior.
        -> 5) Se devuelve la vista
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // Paso 1)
        val vista:View = View.inflate(parent?.context, R.layout.leyenda_item, null)

        // Paso 2)
        val leyenda = this.listaLeyendas[position]

        //Paso 3)
        val imagen_leyenda = vista.findViewById<ImageView>(R.id.leyenda_imagen)
        val nombre_leyenda = vista.findViewById<TextView>(R.id.leyenda_nombre)
        val descripcion_pequena = vista.findViewById<TextView>(R.id.pequena_descripcion)

        // Paso 4)
        var imagen = leyenda.imagen
        Glide.with(vista).load(leyenda.imagen).into(imagen_leyenda);
        nombre_leyenda.text = leyenda.nombre
        var text_desc = leyenda.descripcion.take(50)+"..."
        descripcion_pequena.text = text_desc

        //Paso 5)
        return vista
    }

    /*
    Para una determinada posición dada como argumento se devuelve
    la leyenda asociada a dicha posición de la lista de leyendas.
     */
    override fun getItem(position: Int): Any {
        return listaLeyendas[position]
    }

    /*
    En este método, dada una posición como parámetro se devuelve un identificador,
    en este caso al ser una lista, es la misma posición.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /*
    Este método devuelve la longitud de la lista de leyendas.
     */
    override fun getCount(): Int {
        return listaLeyendas.size
    }
}