package com.acl.leyendasdelaalhambra

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.util.*

class AccesoDatos {
    lateinit var listaLeyendas:MutableList<Leyenda>;
    lateinit var recorridos:MutableList<Recorrido>

    constructor(context: Context?){
        listaLeyendas = obtener_datos_leyendas_json(context)
        recorridos = obtener_datos_recorridos_json(context)
    }

    private fun obtener_datos_leyendas_json(context: Context?): MutableList<Leyenda> {
        //Hay que leer el archivo JSON y parsearlo a un objeto leyenda
        //Extraemos en string el Json
        val leyendas_texto:String

        var inputStream:InputStream = context?.assets!!.open("leyends.json")
        if(Locale.getDefault().getLanguage() == "es"){
            inputStream = context?.assets!!.open("leyendas.json")
        }


        leyendas_texto = inputStream.bufferedReader().use{it.readText()}

        var listaLeyendas:MutableList<Leyenda>

        val gson = Gson()
        val tipo_a_leer = object :TypeToken<MutableList<Leyenda>>() {}.type

        listaLeyendas = gson.fromJson<MutableList<Leyenda>>(leyendas_texto, tipo_a_leer)

        return listaLeyendas

    }

    private fun obtener_datos_recorridos_json(context: Context?): MutableList<Recorrido> {
        //Hay que leer el archivo JSON y parsearlo a un objeto recorrido
        //Extraemos en string el Json
        val recorridos_texto:String

        var inputStream:InputStream = context?.assets!!.open("tours.json")
        if(Locale.getDefault().getLanguage() == "es"){
            inputStream = context?.assets!!.open("recorridos.json")
        }


        recorridos_texto = inputStream.bufferedReader().use{it.readText()}

        var listaRecorridos:MutableList<Recorrido>

        val gson = Gson()
        val tipo_a_leer = object :TypeToken<MutableList<Recorrido>>() {}.type

        listaRecorridos = gson.fromJson<MutableList<Recorrido>>(recorridos_texto, tipo_a_leer)
        for(recorrido in listaRecorridos){
            recorrido.leyendas = obtenerLeyendasRecorrido(recorrido.nombre_recorrido)
        }

        return listaRecorridos

    }

    public fun obtenerLeyendas():MutableList<Leyenda>{
        return listaLeyendas;
    }

    public fun obtenerRecorridos():MutableList<Recorrido>{
        return recorridos;
    }

    public fun obtenerLeyendasRecorrido(recorrido:String):MutableList<Leyenda>{
        var a_devolver = mutableListOf<Leyenda>();

        for(leyenda in listaLeyendas){
            if(leyenda.recorrido == recorrido){
                a_devolver.add(leyenda);
            }
        }
        return a_devolver;
    }
}

//Singleton, de esta forma solo se leen una vez los datos
private lateinit var instancia_datos:AccesoDatos
public fun obtenerAccesoDatos(context:Context):AccesoDatos{
    if(!::instancia_datos.isInitialized){
        instancia_datos = AccesoDatos(context)
    }
    return instancia_datos
}