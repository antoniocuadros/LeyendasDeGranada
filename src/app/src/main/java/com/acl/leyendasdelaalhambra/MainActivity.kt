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


        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.menu_inferior)
        val navController = findNavController(R.id.fragment2)
        bottomNavigationMenu.setupWithNavController(navController)
    }

   

    fun onLeyendaSelected(it: Leyenda) {
        findNavController(R.id.fragment2).navigate(ListaLeyendasFragmentDirections.actionListaLeyendasFragmentToLeyendaDetallesFragment())
    }
}