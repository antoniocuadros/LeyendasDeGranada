package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class PantallaInicio : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var vista = inflater.inflate(R.layout.fragment_pantalla_inicio, container, false)

        val view_padre = requireActivity().findViewById<View>(R.id.menu_inferior)
        val boton_mapa = vista.findViewById<CardView>(R.id.mapa_carta)
        val boton_recorridos = vista.findViewById<CardView>(R.id.recorrido_carta)
        val boton_leyendas = vista.findViewById<CardView>(R.id.leyendas_carta)

        boton_mapa.setOnClickListener {
            (activity as MainActivity).desde_inicio_a("mapa")
        }

        boton_recorridos.setOnClickListener {
            (activity as MainActivity).desde_inicio_a("recorridos")
        }

        boton_leyendas.setOnClickListener {
            (activity as MainActivity).desde_inicio_a("leyendas")
        }

        //escondemos el menu inferior
        view_padre.visibility = View.GONE

        return vista
    }

}