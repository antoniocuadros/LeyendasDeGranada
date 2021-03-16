package com.acl.leyendasdelaalhambra

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leyendas")
data class Leyenda(@PrimaryKey val id: Long, val nombre: String, val descripcion: String,
                   val imagen: String, val Long: Double, val Lat: Double,
                   val recorrido: String)
