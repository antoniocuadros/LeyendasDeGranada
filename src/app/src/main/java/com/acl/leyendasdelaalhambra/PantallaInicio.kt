package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val view = requireActivity().findViewById<View>(R.id.menu_inferior)

        view.visibility = View.GONE

        return vista
    }

}