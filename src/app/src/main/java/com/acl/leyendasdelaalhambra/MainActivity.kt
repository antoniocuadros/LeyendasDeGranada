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

/*
Esta clase representa la actividad principal de la aplicación. Esta actividad principal
estará formada por un menu inferior y el resto de la aplicación son fragmentos que se irán
alternando en función de los botones de navegación definidos en el menú inferior.
 */
class MainActivity : AppCompatActivity() {
    /*
    El método onCreate de cualquier actividad es llamado cuando se crea inicialmente la actividad.
    Se vincula la vista y adicionalmente se establece el menú inferior, y su navController para
    que al pulsar se redirija al fragmento correspondiente definido según lo que se puede ver
    en el Navigation component.
     */
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

    /*
    De aquí en adelante se encuentran los métodos relacionados con los intercambios de fragmentos
    y que información se pasan entre unos y otros. El flujo de datos entre fragments no se recomienda
    hacerlo directamente entre fragments, se recomienda que los datos que se transmitan pasen por la
    actividad padre, es decir esta, tal y como se hace en los métodos siguientes.
     */


    //De lista leyendas a detalles
    fun onLeyendaSelected(leyenda: Leyenda) {
        findNavController(R.id.fragment2).navigate(ListaLeyendasFragmentDirections.actionListaLeyendasFragmentToLeyendaDetallesFragment(leyenda))
    }


    /*
    Este método se encarga de definir la navegación entre el fragmento de mapas y el de detalles de una leyenda
    cuando se pulse sobre un marcador en el mapa para obtener más información.
    Desde mapa a detalles se pasa la leyenda que se desea mostrar.
     */
    fun onMarcadorSelected(leyenda: Leyenda) {
        findNavController(R.id.fragment2).navigate(MapaFragmentDirections.actionIconoMapaToLeyendaDetallesFragment(leyenda))
    }

    /*
    Este método se encarga de definir la navegación entre el fragmento de detalles y el de mapa cuando
    se pulse el botón de localización en los detalles de la leyenda.
    Se pasa la leyenda que se desea ubicar y visualizar en el mapa.
     */
    fun onBotonLocalizacionSelected(leyenda: Leyenda) {
        findNavController(R.id.fragment2).navigate(LeyendaDetallesFragmentDirections.actionLeyendaDetallesFragmentToIconoMapa(leyenda))
    }


    /*
    Este método se encarga de definir la navegación entre el fragmento de recorridos y el del mapa
    para mostrar un determinado recorrido seleccionado.
    Desde recorridos a mapa se pasa el objeto recorrido seleccionado.
     */
    fun onRecorridoSelected(recorrido: Recorrido) {
        findNavController(R.id.fragment2).navigate(RecorridosFragmentDirections.actionIconoRecorridosToIconoMapa(null, recorrido))
    }

    /*
    Este método se encarga de proporcionar navegabilidad desde la pantalla de inicio al resto de
    fragmentos sin pasar ningún argumento en función de a donde se haya pulsado.
    Desde la pantalla de inicio se puede ir al fragmento:
        -> Mapa.
        -> Lista de leyendas.
        -> Lista de recorridos.
     */
    fun desde_inicio_a(donde:String){
        if(donde == "mapa"){
            findNavController(R.id.fragment2).navigate(PantallaInicioDirections.actionPantallaInicioToIconoMapa())
        }
        else{
            if(donde == "recorridos"){
                findNavController(R.id.fragment2).navigate(PantallaInicioDirections.actionPantallaInicioToIconoRecorridos())
            }
            else{
                findNavController(R.id.fragment2).navigate(PantallaInicioDirections.actionPantallaInicioToIconoListaLeyendas())
            }
        }
    }


    /*
    Este método da soporte para navegar desde el fragmento del mapa al de leyendas cuando se pulsa el botón
    de atrás ya que leyendas se ha definido como la pantalla principal
     */
    fun de_mapa_a_leyendas(){
        findNavController(R.id.fragment2).navigate(MapaFragmentDirections.actionIconoMapaToIconoListaLeyendas())
    }


    /*
    Este método da soporte para navegar desde el fragmento de recorridos al de leyendas cuando se pulsa el botón
    de atrás ya que leyendas se ha definido como la pantalla principal
     */
    fun de_recorridos_a_leyendas(){
        findNavController(R.id.fragment2).navigate(RecorridosFragmentDirections.actionIconoRecorridosToIconoListaLeyendas())
    }


    /*
    Este método da soporte para navegar desde el fragmento de detalles al de leyendas cuando se pulsa el botón
    de atrás ya que leyendas se ha definido como la pantalla principal
     */
    fun de_detalles_a_leyendas(){
        findNavController(R.id.fragment2).navigate(LeyendaDetallesFragmentDirections.actionLeyendaDetallesFragmentToIconoListaLeyendas())
    }

    fun de_ajustes_a_leyendas(){
        findNavController(R.id.fragment2).navigate(ajustesFragmentDirections.actionIconoAjustesToIconoListaLeyendas())
    }
}