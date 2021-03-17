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
            (activity as MainActivity).onLeyendaSelected(it)
        }

        val leyendaList = mutableListOf(
            Leyenda(1, "Leyenda del Patio de los Leones",
                    "Hace muchos años una Princesa llamada Zaira, viajó hasta Granada con su padre. Se alojaron en las dependencias de La Alhambra. Zaira se sentía muy sola ya que solo contaba con la compañía de su padre y un séquito de 11 hombre que velaban por su seguridad. Un día, la Princesa leyó el diario de su padre donde contaba que había matado a los verdaderos padres de Zaira cuando ella tenía 1 año.Al parecer, su madre había echado un maleficio al talismán y si la niña algún día se enteraba de la verdad, una maldición caería sobre el Rey. En el patio donde ahora se encuentra el Patio de Los Leones, la joven le preguntó a su padre si lo que había leído era cierto, su padre lo afirmó y cuenta la leyenda que el talismán se activó y su padre con los 11 hombres se convirtieron en 12 leones de piedra, los que coronan hoy en día el Patio de los Leones.",
                    "leones",
                    1.1231,
                    2.21313,
                    "Recorrido 1"),
            Leyenda(1, "Leyenda de la Puerta de la Justicia",
                    "Cuenta una leyenda sobre la puerta de la Justicia, relacionada con la construcción misma de la Alhambra. Siempre se ha hablado de la dedicación puesta en la construcción de la Alhambra, tanto en lo decorativo como en lo arquitectónico. Se asegura que tan sumamente recia era su construcción que, aún recibiendo el ataque de mil ejércitos enemigos, jamás caería. Así pues, el día que la llave del arco interior de la Puerta de la Justicia y la mano de su arco exterior se unan, es decir, si la Alhambra cae, será por que ha llegado el fin del mundo.",
                    "justicia",
                    1.1231,
                    2.21313,
                    "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones3", "Descripción de prueba", "https://i.imgur.com/ABQw1si.jpg", 1.1231, 2.21313, "Recorrido 1"),
            Leyenda(1, "Leyenda del Patio de los Leones4", "Descripción de prueba", "https://i.imgur.com/QOsNgGs.jpg", 1.1231, 2.21313, "Recorrido 1"),
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