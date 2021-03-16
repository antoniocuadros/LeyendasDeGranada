package com.acl.leyendasdelaalhambra.BaseDatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.acl.leyendasdelaalhambra.Leyenda

@Database(entities = [Leyenda::class], version = 1)
abstract class LeyendasDataBase:RoomDatabase(){
    abstract val leyendasDao: leyendasDao

    private lateinit var instancia_db: LeyendasDataBase

    fun obtenerDatabase(context: Context): LeyendasDataBase{
        if(!::instancia_db.isInitialized){
            instancia_db = Room.databaseBuilder(context.applicationContext,
                LeyendasDataBase::class.java, "leyendas_db").build()
        }
        return instancia_db
    }
}
