package com.acl.leyendasdelaalhambra

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "leyendas")
@Parcelize
data class Leyenda(@PrimaryKey val id: Long, val nombre: String, val descripcion: String,
                   val imagen: String, val Lat: Double, val Long: Double,
                   val recorrido: String) : Parcelable
