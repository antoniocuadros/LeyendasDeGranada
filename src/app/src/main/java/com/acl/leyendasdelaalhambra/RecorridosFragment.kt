package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_recorridos.*

class RecorridosFragment : Fragment() {
    lateinit var adapter:RecorridoAdapter
    lateinit var recorridos:MutableList<Recorrido>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recorridos, container, false)

        val acceso_datos = AccesoDatos()
        val recorridos = acceso_datos.obtenerRecorridos()
        val cuadricula = view.findViewById<GridView>(R.id.cuadricula_leyendas)

        adapter = RecorridoAdapter(recorridos)

        cuadricula.adapter = adapter



        return view
    }
}