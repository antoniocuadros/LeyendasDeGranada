package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.activity.addCallback

class RecorridosFragment : Fragment() {
    lateinit var adapter:RecorridoAdapter
    lateinit var recorridos:MutableList<Recorrido>
    lateinit var cuadricula:GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            (activity as MainActivity).de_recorridos_a_leyendas()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recorridos, container, false)

        //Mostramos el menu inferior
        var menu = requireActivity().findViewById<View>(R.id.menu_inferior)
        menu.visibility = View.VISIBLE

        val acceso_datos = AccesoDatos(context)
        val recorridos = acceso_datos.obtenerRecorridos()
        cuadricula = view.findViewById<GridView>(R.id.cuadricula_leyendas)

        adapter = RecorridoAdapter(recorridos)

        cuadricula.adapter = adapter

        cuadricula.setOnItemClickListener{cuadricula, view, i,l ->
            val recorrido:Recorrido
            recorrido = cuadricula.getItemAtPosition(i) as Recorrido
            (activity as MainActivity).onRecorridoSelected(recorrido)
        }

        return view
    }
}