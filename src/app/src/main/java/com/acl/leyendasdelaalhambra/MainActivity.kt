package com.acl.leyendasdelaalhambra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapaFragment = MapaFragment()
        val recorridosFragment = RecorridosFragment()
        val listaLeyendasFragment = ListaLeyendasFragment()

        cambiaFragment(mapaFragment, false)

        menu_inferior.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.icono_mapa -> cambiaFragment(mapaFragment, true)
                R.id.icono_recorridos -> cambiaFragment(recorridosFragment, true)
                R.id.icono_lista_leyendas -> cambiaFragment(listaLeyendasFragment, true)
            }
            true
        }
    }

    fun cambiaFragment(fragmento: Fragment, vuelta_atras:Boolean){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.cambia_fragmento, fragmento)
            commit()
            if(vuelta_atras){
                addToBackStack(null)
            }
        }
    }
}