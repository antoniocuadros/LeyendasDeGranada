package com.acl.leyendasdelaalhambra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/*
    Esta clase representa el adaptador del slider de imágenes que se mostrará en los detalles de una leyenda.
    Este adaptador se encargará de rellenar la vista del slider de imágenes con las imágenes según el layout
    definido en "item_imagen_slider"
    Como se puede ver, al igual que el resto de adapters, es necesario pasarle la lista de objetos a mostrar,
    en este caso las URLs de las imágenes.
    .
 */
class SliderImagenesAdapter(var imagen:MutableList<String>,context: Context):RecyclerView.Adapter<SliderImagenesAdapter.Pager2ViewHolder>() {
    var context = context
    /*
    En este punto es necesario definir una inner class que define el contenido de las vistas que queremos
    acceder, en este caso únicamente una, que es la propia imagen en sí.
     */
    inner class Pager2ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val  imagen = itemView.findViewById<ImageView>(R.id.imagen_slider)
    }


    /*
    En este método se infla la vista de cada item, pero aún sin asignar el contenido
     */
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): SliderImagenesAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_imagen_slider, parent, false))
    }

    /*
    Método que devuelve el tamaño de la lista de imágenes.
     */
    override fun getItemCount(): Int {
        return imagen.size
    }

    /*
    Este método se encarga de dotar de contenido a cada item que se añade al RecyclerView de imágenes.
    En este caso solo es necesario añadir la imagen cargándola directamente con Glide.
     */
    override fun onBindViewHolder(holder: SliderImagenesAdapter.Pager2ViewHolder, position: Int) {
        var id_imagen = context.resources.getIdentifier(imagen[position], "drawable", "com.acl.leyendasdelaalhambra")
        holder.imagen.setImageResource(id_imagen)
    }

}