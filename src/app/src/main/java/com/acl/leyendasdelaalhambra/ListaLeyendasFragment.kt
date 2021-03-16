package com.acl.leyendasdelaalhambra

import android.content.Context
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
            (activity as MainActivity).onLeyendaSelected(it)
        }

        val leyendaList = mutableListOf(
            Leyenda(1, "Leyenda del Patio de los Leones", "Hace muchos años una Princesa llamada Zaira, viajó hasta Granada con su padre. Se alojaron en las dependencias de La Alhambra. Zaira se sentía muy sola ya que solo contaba con la compañía de su padre y un séquito de 11 hombre que velaban por su seguridad. Un día, la Princesa leyó el diario de su padre donde contaba que había matado a los verdaderos padres de Zaira cuando ella tenía 1 año.Al parecer, su madre había echado un maleficio al talismán y si la niña algún día se enteraba de la verdad, una maldición caería sobre el Rey. En el patio donde ahora se encuentra el Patio de Los Leones, la joven le preguntó a su padre si lo que había leído era cierto, su padre lo afirmó y cuenta la leyenda que el talismán se activó y su padre con los 11 hombres se convirtieron en 12 leones de piedra, los que coronan hoy en día el Patio de los Leones.", "url_imagen_prueba", 1.1231, 2.21313, "Recorrido 1"),
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