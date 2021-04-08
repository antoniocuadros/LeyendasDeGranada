package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.activity.addCallback
import kotlin.system.exitProcess

/*
    Este fragmento dota de funcionalidad a la vista que muestra una lista de leyendas
 */
class ListaLeyendasFragment : Fragment() {
    /*
    ATRIBUTOS DE LA CLASE ListaLeyendasFragment:
        -> adapter: Adaptador de tipo LeyendaAdapter que convertirá cada leyenda en un elemento de la lista.
        -> leyendas: MutableList de tipo Leyenda que contiene el conjunto de leyendas a mostrar.
        -> cuadricula_leyendas: Vista de tipo GridView que contiene el listado de leyendas.
        -> var menu = Vista de tipo View para mostrar el menu.
     */
    lateinit var adapter:LeyendaAdapter
    lateinit var leyendas:MutableList<Leyenda>
    lateinit var cuadricula_leyendas: GridView
    lateinit var menu:View

    /*
    El método onCreate de cualquier Fragment es llamado cuando se crea inicialmente el fragmento,
    se llama al método onCreate de la clase superior, Fragment para crear el fragmento.
    Adicionalmente se añade el comportamiento del botón de vuelta atrás diciendo que
    si se pulsa ese botón en este fragment se termine la aplicación, se quiere conseguir
    que esta sea la pantalla principal y al volver atrás se salga de la aplicación.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this){
            exitProcess(0)
        }
    }


    /*
    El método onCreateView de un fragmento crea y devuelve la jerarquía de la vista asociada con el
    fragmento.
    Adicionalmente de forma específica a este fragmento se realizan los siguientes pasos:
        -> Paso 1): Se infla y obtiene la vista
        -> Paso 2): Se establece como visible el menú inferior y se inicializa la vista del GridView
        -> Paso 3): Se obtienen las leyendas
        -> Paso 4): Se define el adaptador que convertirá cada leyenda en un item de la lista.
        -> Paso 5): Se define un clickListener para cada elemento de la lista de tal manera que al
                    hacer click en una leyenda nos llevará a la pestaña de detalles
        -> Paso 6): Se devuelve la vista
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Paso 1)
        val view = inflater.inflate(R.layout.fragment_lista_leyendas, container, false)

        // Paso 2)
        menu = requireActivity().findViewById<View>(R.id.menu_inferior)
        cuadricula_leyendas = view.findViewById<GridView>(R.id.cuadricula_leyendas)
        menu.visibility = View.VISIBLE

        // Paso 3)
        val acceso_datos = AccesoDatos(context)
        val leyendas = acceso_datos.obtenerLeyendas()

        // Paso 4)
        adapter = LeyendaAdapter(leyendas)
        cuadricula_leyendas.adapter = adapter

        //Paso 5)
        cuadricula_leyendas.setOnItemClickListener{cuadricula_leyendas, _, i,_ ->
            val leyenda:Leyenda
            leyenda = cuadricula_leyendas.getItemAtPosition(i) as Leyenda
            (activity as MainActivity).onLeyendaSelected(leyenda)
        }

        // Paso 6
        return view
    }
}