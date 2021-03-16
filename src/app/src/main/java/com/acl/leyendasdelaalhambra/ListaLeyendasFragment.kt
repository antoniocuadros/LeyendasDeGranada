package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaLeyendasFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_lista_leyendas, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_leyendas)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = LeyendaAdapter()
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            Toast.makeText(requireActivity(), it.nombre, Toast.LENGTH_LONG).show()
        }

        val leyendaList = mutableListOf(
            Leyenda(1, "Leyenda del Patio de los Leones", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones2", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones3", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones4", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones5", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones6", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones7", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones8", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones9", "Descripción de prueba", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1")

        )

        adapter.submitList(leyendaList)
        return view
    }
}