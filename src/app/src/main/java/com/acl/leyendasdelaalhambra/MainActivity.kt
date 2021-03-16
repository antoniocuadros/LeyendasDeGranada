package com.acl.leyendasdelaalhambra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Para hacer que el menu inferior se asocie a cada fragment
        // para ello es necesario que en el controlador de navegacion
        // y el propio fragment tengan el mismo ID
        val menu_inferior = findViewById<BottomNavigationView>(R.id.menu_inferior)
        val controlador_navegacion = findNavController(R.id.fragment2)
        menu_inferior.setupWithNavController(controlador_navegacion)
    }



    fun onLeyendaSelected(it: Leyenda) {
        findNavController(R.id.fragment2).navigate(ListaLeyendasFragmentDirections.actionListaLeyendasFragmentToLeyendaDetallesFragment())
    }
}