package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

class LeyendaDetallesFragment : Fragment() {
    private val argumentos_recibidos_leyendas: LeyendaDetallesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_leyenda_detalles, container, false)

        val leyenda = argumentos_recibidos_leyendas.leyenda

        val nombreText = view.findViewById<TextView>(R.id.nombre_leyenda_detalles)
        val descripcionText = view.findViewById<TextView>(R.id.descripcion_leyenda_detalles)

        nombreText.text = leyenda.nombre
        descripcionText.text = leyenda.descripcion

        return view
    }
}