package com.acl.leyendasdelaalhambra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_mapa.*


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



    //De lista leyendas a detalles
    fun onLeyendaSelected(leyenda: Leyenda) {
        findNavController(R.id.fragment2).navigate(ListaLeyendasFragmentDirections.actionListaLeyendasFragmentToLeyendaDetallesFragment(leyenda))
    }

    //De mapa a detalles
    fun onMarcadorSelected(leyenda: Leyenda) {
        findNavController(R.id.fragment2).navigate(MapaFragmentDirections.actionIconoMapaToLeyendaDetallesFragment(leyenda))
    }

    //De detalles a mapa
    fun onBotonLocalizacionSelected(leyenda: Leyenda) {
        findNavController(R.id.fragment2).navigate(LeyendaDetallesFragmentDirections.actionLeyendaDetallesFragmentToIconoMapa(leyenda))
    }

    //De recorrido a mapa
    fun onRecorridoSelected(recorrido: Recorrido) {
        findNavController(R.id.fragment2).navigate(RecorridosFragmentDirections.actionIconoRecorridosToIconoMapa(null, recorrido))
    }
}