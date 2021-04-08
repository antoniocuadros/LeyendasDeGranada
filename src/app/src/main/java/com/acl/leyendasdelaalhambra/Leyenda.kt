package com.acl.leyendasdelaalhambra

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/*
Esta clase de datos representa una leyenda con los siguientes atributos:
    -> id: identificador numérico de tipo Int que identifica de forma única a cada leyenda.
    -> nombre: nombre de la leyenda de tipo String.
    -> descripcion: descripción de la leyenda almacenada como String.
    -> imagen: url de la imagen que será cargada haciendo uso de Glide, de tipo string.
    -> Lat: Latitud de la ubicación donde toma lugar la leyenda, de tipo Double.
    -> Long: Longitud de la ubicación donde toma lugar la leyenda, de tipo Double.
    -> recorrido: Identificador del recorrido al que pertenece la leyenda, de tipo String.
    -> ubicacion: Ubicación más específica del lugar donde toma lugar la leyenda, de tipo String.
    -> orden: Orden de visita dentro del recorrido, de tipo Int.
    -> fuente: Fuente (url a página de internet) de donde se ha obtenido la leyenda, de tipo String.
    -> imagenes_adicionales: Lista de urls a imágenes adicionales (opcional) que puede tener una leyends, de tipo MutableList<String>.
    -> sitio_web: Sitio web opcional que contiene información del lugar donde toma lugar la leyenda.
    -> id_musica: Identificador del archivo multimedia que contiene la descripción de audio de la leyenda.
 */
@Parcelize
data class Leyenda(val id: Long,
                   val nombre: String,
                   val descripcion: String,
                   val imagen: String,
                   val Lat: Double,
                   val Long: Double,
                   val recorrido: String,
                   val ubicacion:String,
                   val orden:Int,
                   val fuente:String,
                   val imagenes_adicionales:MutableList<String>,
                   val sitio_web:String,
                   var id_musica:String
                   ) : Parcelable
