package com.acl.leyendasdelaalhambra

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.util.*

/*
    Esta clase es la encargada de realizar el acceso a los datos para obtener tanto las leyendas
    como los recorridos en un determinado idioma, por defecto inglés, si bien el dispositivo o en
    ajustes previos no se ha cambiado el idioma a español. El acceso a datos se hace mediante la
    lectura y parseo de ficheros JSON que se encuentran en la carpeta assets y son los siguientes:
        -> Ficheros en español:
            -> leyendas.json
            -> recorridos.json
        -> Ficheros en inglés
            -> leyends.json
            -> tours.json
 */
class AccesoDatos {
    /*
    ATRIBUTOS DE LA CLASE AccesoDatos:
        -> listaLeyendas: MutableList de objetos Leyenda que contendrá la lista de leyendas leídas
                          de un fichero JSON.
        -> recorridos: Mutable list de objetos Recorrido que contendrá la lista de recorridos leídos
                          de un fichero JSON.
     */
    lateinit var listaLeyendas:MutableList<Leyenda>;
    lateinit var recorridos:MutableList<Recorrido>

    /*
    El constructor de esta clase se encarga de inicializar los atributos listaLeyendas y recorridos
    haciendo uso de los métodos obtener_datos_leyendas_json y obtener_datos_recorridos_json respectivamente
    que se encargarán de abrir el json correspondiente y parsearlo a una lista de objetos, ya sea de
    tipo Leyenda o Recorrido.
     */
    constructor(context: Context?){
        listaLeyendas = obtener_datos_leyendas_json(context)
        recorridos = obtener_datos_recorridos_json(context)
    }

    /*
    Este método se encarga de abrir el fichero JSON correspondiente, lo leerá y transformará su contenido
    en una lista de leyendas. Sigue los siguientes pasos:
        -> 1) Haciendo uso de sharedPreferences se obtiene el idioma almacenado previamente y establecido
              en la pestaña de ajustes, si no se ha establecido nada en esa pestaña, y en consecuencia
              no se obtiene un idioma almacenado persistentemente, se coge por defecto el idioma del dispositivo.
        -> 2) En función del idioma obtenido anteriormente, si se ha obtenido "es" que hace referencia
              al idioma español, se abre el archivo "leyendas.json". Si no es el español, se toma por
              defecto el inglés, leyendo el archivo "leyends.json".

        -> 3) Se carga el contenido del fichero en la variable leyendas_texto.
        -> 4) Haciendo uso de GSON que nos permite parsear el contenido de un fichero en un determinado
              objeto o lista de objetos, parseamos el contenido textual del fichero JSON almacenado en
              leyendas_texto a una lista de leyendas almacenada en la variable listaLeyendas.
        -> 5) Se devuelve la lista de leyendas almacenada en la variable listaLeyendas.
     */
    private fun obtener_datos_leyendas_json(context: Context?): MutableList<Leyenda> {
        val leyendas_texto:String

        // Paso 1)
        var sharedPreferences: SharedPreferences = context!!.applicationContext.getSharedPreferences("ajustes",0)
        var lang = sharedPreferences.getString("lang", Locale.getDefault().getLanguage())


        // Paso 2)
        var inputStream:InputStream = context?.assets!!.open("leyends.json")
        if(lang == "es"){
            inputStream = context?.assets!!.open("leyendas.json")
        }

        // Paso 3)
        leyendas_texto = inputStream.bufferedReader().use{it.readText()}
        var listaLeyendas:MutableList<Leyenda>


        // Paso 4)
        val gson = Gson()
        val tipo_a_leer = object :TypeToken<MutableList<Leyenda>>() {}.type
        listaLeyendas = gson.fromJson<MutableList<Leyenda>>(leyendas_texto, tipo_a_leer)

        //Paso 5)
        return listaLeyendas

    }

    /*
    Este método se encarga de abrir el fichero JSON correspondiente, lo leerá y transformará su contenido
    en una lista de recorridos. Sigue los siguientes pasos:
        -> 1) En función del idioma definido en la aplicación se lee el fichero "recorridos.json"
              si es el español, o "tours.json" en caso por defecto si no es el español para mostrar
              la información en inglés.
        -> 2) Se carga el contenido del fichero en la variable recorridos_texto.
        -> 3) Haciendo uso de GSON que nos permite parsear el contenido de un fichero en un determinado
              objeto o lista de objetos, parseamos el contenido textual del fichero JSON almacenado en
              recorridos_texto a una lista de leyendas almacenada en la variable listaRecorridos.
            -> 3.1) Como en los recorridos almacenamos un String que hace referencia al identificador
                    de recorrido y cada leyenda almacena el identificador del recorrido al que pertenece
                    es necesario recuperar para cada recorrido sus leyendas asociadas y añadirlas a su
                    atributo de la clase en forma de lista. Para ello se hace uso del método obtenerLeyendasRecorrido
                    que se le pasa el identificador del recorrido y devuelve una lista de leyendas pertenecientes
                    a dicho recorrido.
        -> 4) Se devuelve la lista de recorridos.
     */
    private fun obtener_datos_recorridos_json(context: Context?): MutableList<Recorrido> {
        val recorridos_texto:String

        // Paso 1)
        var inputStream:InputStream = context?.assets!!.open("tours.json")
        if(Locale.getDefault().getLanguage() == "es"){
            inputStream = context?.assets!!.open("recorridos.json")
        }

        // Paso 2)
        recorridos_texto = inputStream.bufferedReader().use{it.readText()}
        var listaRecorridos:MutableList<Recorrido>

        // Paso 3)
        val gson = Gson()
        val tipo_a_leer = object :TypeToken<MutableList<Recorrido>>() {}.type
        listaRecorridos = gson.fromJson<MutableList<Recorrido>>(recorridos_texto, tipo_a_leer)
        // Paso 3.1)
        for(recorrido in listaRecorridos){
            recorrido.leyendas = obtenerLeyendasRecorrido(recorrido.nombre_recorrido)
        }

        return listaRecorridos

    }

    /*
    Este método se encarga de devolver las leyendas almacenadas como una lista.
     */
    public fun obtenerLeyendas():MutableList<Leyenda>{
        return listaLeyendas;
    }

    /*
    Este método se encarga de devolver los recorridos almacenados como una lista.
     */
    public fun obtenerRecorridos():MutableList<Recorrido>{
        return recorridos;
    }

    /*
    Este método se encarga de dado un identificador de un recorrido, obtener las leyendas
    que pertenecen a dicho recorrido. Devuelve el conjunto de leyendas asociado al recorrido
    en forma de lista.
     */
    private fun obtenerLeyendasRecorrido(recorrido:String):MutableList<Leyenda>{
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