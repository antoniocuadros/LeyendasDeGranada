package com.acl.leyendasdelaalhambra

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize
/*
Esta clase de datos representa un recorrido con los siguientes atributos:
    -> id: Identificador único de cada recorrido, tipo Long.
    -> nombre: Nombre del recorrido, tipo String.
    -> descripcion: Breve descripción del recorrido, tipo String.
    -> imagen: Imagen portada del recorrido, una URL para Glide, tipo String.
    -> nombre_recorrido: Nombre clave del recorrido para referencias leyendas, tipo String.
    -> leyendas: Lista de leyendas asociadas al recorrido, se cargan en tiempo de ejecución, tipo MutableList<Leyenda>.
    -> Latitud: Latitud de la ubicación donde toma lugar el recorrido, de tipo Double.
    -> Longitud: Longitud de la ubicación donde toma lugar el recorrido, de tipo Double.
    -> zoom: Zoom por defecto al mostrar el recorrido, tipo Float.

 */
@Parcelize
data class Recorrido(val id: Long,
                     val nombre: String,
                     val descripcion: String,
                     val imagen: String,
                     val nombre_recorrido:String,
                     var leyendas:MutableList<Leyenda>,
                     var Latitud:Double,
                     var Longitud: Double,
                     var zoom:Float
                     ) : Parcelable