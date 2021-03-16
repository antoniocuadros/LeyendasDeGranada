package com.acl.leyendasdelaalhambra.BaseDatos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acl.leyendasdelaalhambra.Leyenda

@Dao
interface leyendasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun anadeLeyendas(listaLeyendas: MutableList<Leyenda>)

    @Query("SELECT * FROM leyendas")
    fun obtenerLeyendas():MutableList<Leyenda>
}

