package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.activity.addCallback

/*
    Este fragmento dota de funcionalidad a la vista que muestra una lista de recorridos
 */
class RecorridosFragment : Fragment() {
    /*
    ATRIBUTOS DE LA CLASE RecorridosFragment:
        -> adapter: Adaptador de tipo RecorridoAdapter que convertirá cada recorrido en un elemento de la lista.
        -> recorridos: MutableList de tipo Recorrido que contiene el conjunto de recorridos a mostrar.
        -> cuadricula_leyendas: Vista de tipo GridView que contiene el listado de recorridos.
        -> var menu = Vista de tipo View para mostrar el menu.
     */
    lateinit var adapter:RecorridoAdapter
    lateinit var recorridos:MutableList<Recorrido>
    lateinit var cuadricula:GridView
    lateinit var menu:View

    /*
    El método onCreate de cualquier Fragment es llamado cuando se crea inicialmente el fragmento,
    se llama al método onCreate de la clase superior, Fragment para crear el fragmento.
    Adicionalmente se añade el comportamiento del botón de vuelta atrás diciendo que
    si se pulsa ese botón en este fragment se vuelva a la lista de leyendas, se quiere conseguir
    que la lista de leyendas sea la pantalla principal.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this){
            (activity as MainActivity).de_recorridos_a_leyendas()
        }

    }

    /*
        El método onCreateView de un fragmento crea y devuelve la jerarquía de la vista asociada con el
        fragmento.
        Adicionalmente de forma específica a este fragmento se realizan los siguientes pasos:
            -> Paso 1): Se infla y obtiene la vista
            -> Paso 2): Se establece como visible el menú inferior y se inicializa la vista del GridView
            -> Paso 3): Se obtienen los recorridos
            -> Paso 4): Se define el adaptador que convertirá cada recorrido en un item de la lista.
            -> Paso 5): Se define un clickListener para cada elemento de la lista de tal manera que al
                        hacer click en un recorrido nos llevará a la pestaña de detalles.
            -> Paso 6): Se devuelve la vista.
         */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Paso 1)
        val view = inflater.inflate(R.layout.fragment_recorridos, container, false)

        // Paso 2)
        menu = requireActivity().findViewById<View>(R.id.menu_inferior)
        cuadricula = view.findViewById<GridView>(R.id.cuadricula_leyendas)
        menu.visibility = View.VISIBLE

        // Paso 3)
        val acceso_datos = AccesoDatos(context)
        val recorridos = acceso_datos.obtenerRecorridos()

        // Paso 4
        adapter = RecorridoAdapter(recorridos)
        cuadricula.adapter = adapter

        // Paso 5
        cuadricula.setOnItemClickListener{cuadricula, _, i,_ ->
            val recorrido:Recorrido
            recorrido = cuadricula.getItemAtPosition(i) as Recorrido
            (activity as MainActivity).onRecorridoSelected(recorrido)
        }

        // Paso 6
        return view
    }
}