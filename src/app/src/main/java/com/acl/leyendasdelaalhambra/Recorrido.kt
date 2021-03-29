package com.acl.leyendasdelaalhambra

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recorrido(val id: Long,
                     val nombre: String,
                     val descripcion: String,
                     val imagen: String,
                     val nombre_recorrido:String,
                     var leyendas:MutableList<Leyenda>,
                     var LatitudLongitud:LatLng,
                     var zoom:Float
                     ) : Parcelable