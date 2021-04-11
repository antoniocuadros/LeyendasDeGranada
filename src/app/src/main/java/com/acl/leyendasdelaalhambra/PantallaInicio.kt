package com.acl.leyendasdelaalhambra

import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
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
import java.util.*

/*
    Este fragmento dota de funcionalidad a la vista de la pantalla de inicio. Para ello situa un
    título y tres botones, para ir a leyendas, recorridos o mapa.
 */
class PantallaInicio : Fragment() {
    /*
    ATRIBUTOS DE LA CLASE PantallaInicio:
    -> view_padre: Vista de tipo View que representa la view de la actividad padre.
    -> boton_mapa: Vista de tipo CardView que representa el botón para ir a la pantalla mapa.
    -> boton_recorridos: Vista de tipo CardView que representa el botón para ir a la pantalla recorridos.
    -> boton_leyendas: Vista de tipo CardView que representa el botón para ir a la pantalla leyendas.
    */
    lateinit var view_padre:View
    lateinit var boton_mapa:CardView
    lateinit var boton_recorridos:CardView
    lateinit var boton_leyendas:CardView
    /*
    El método onCreate de cualquier Fragment es llamado cuando se crea inicialmente el fragmento,
    se llama al método onCreate de la clase superior, Fragment para crear el fragmento.
    Adicionalmente en función de las sharedPreferences establecidas en otras sesiones se cambia el
    idioma. Si no hay nada guardado de sesiones previas se establece como principal el idioma del
    teléfono siendo inglés por defecto si no es el español.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var sharedPreferences: SharedPreferences = requireContext().applicationContext.getSharedPreferences("ajustes",0)
        var lang = sharedPreferences.getString("lang", Locale.getDefault().getLanguage())

        changeLocale(lang.toString())
    }

    /*
    El método onCreateView de un fragmento crea y devuelve la jerarquía de la vista asociada con el
    fragmento.
    Adicionalmente se llama al método inicializa_vistas que vincula cada atributo de la clase
    con su vista.
    Por último se definen los listeners de los botones que nos redirigirán a los distintos fragmentos
    de nuestra aplicación:
        -> boton_mapa: al hacer click nos redirige al mapa.
        -> boton_recorridos: al hacer click nos redirige a la lista de recorridos.
        -> boton_leyendas: al hacer click nos redirige a la lista de leyendas.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var vista = inflater.inflate(R.layout.fragment_pantalla_inicio, container, false)

        inicializa_vistas(vista)

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

    /*
    Asocia cada atributo de la clase con su correspondiente vista del layout para poder
    definir los eventos necesarios.
     */
    private fun inicializa_vistas(vista:View){
        view_padre = requireActivity().findViewById<View>(R.id.menu_inferior)
        boton_mapa = vista.findViewById<CardView>(R.id.mapa_carta)
        boton_recorridos = vista.findViewById<CardView>(R.id.recorrido_carta)
        boton_leyendas = vista.findViewById<CardView>(R.id.leyendas_carta)
    }

    /*
    Este método se encarga de cambiar el idioma de la aplicación justo al inicio en función del idioma
    del teléfono o el idioma definido previamente.
     */
    private fun changeLocale(id:String){
        var locale = Locale(id)
        val configuracion = Configuration()
        val resources: Resources = requireContext().resources

        configuracion.setLocale(Locale(id))
        Locale.setDefault(locale)
        resources.updateConfiguration(configuracion, resources.getDisplayMetrics())
    }
}