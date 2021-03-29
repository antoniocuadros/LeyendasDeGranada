package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

class ListaLeyendasFragment : Fragment() {
    lateinit var adapter:LeyendaAdapter
    lateinit var leyendas:MutableList<Leyenda>
    lateinit var cuadricula_leyendas: GridView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_leyendas, container, false)

        val acceso_datos = AccesoDatos(context)
        val leyendas = acceso_datos.obtenerLeyendas()
        cuadricula_leyendas = view.findViewById<GridView>(R.id.cuadricula_leyendas)

        adapter = LeyendaAdapter(leyendas)

        cuadricula_leyendas.adapter = adapter

        cuadricula_leyendas.setOnItemClickListener{cuadricula_leyendas, view, i,l ->
            val leyenda:Leyenda
            leyenda = cuadricula_leyendas.getItemAtPosition(i) as Leyenda
            (activity as MainActivity).onLeyendaSelected(leyenda)
        }

        return view
    }
}