package com.acl.leyendasdelaalhambra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_mapa.*

class MapaFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mapa: GoogleMap;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var vista =  inflater.inflate(R.layout.fragment_mapa, container, false)

        //inicializamos el fragmento
        inicializaFragmento();

        return vista;
    }

    private fun inicializaFragmento(){
        val vista_fragmento = childFragmentManager.findFragmentById(R.id.mapa_fragmento) as SupportMapFragment;
        //Genera el mapa de forma asíncrona
        vista_fragmento.getMapAsync(this);
        //Después de ejecutar lo anterior se pasa a ejecutar la función onMapReady
    }

    //se llama cuando se cargue el mapa
    override fun onMapReady(_mapa: GoogleMap) {
        mapa = _mapa;
        centra_en_alhambra();

        val accesoDatos = AccesoDatos()
        val leyendas = accesoDatos.obtenerLeyendas()
        anadirMarcadoresLeyendas(leyendas)
    }

    private fun centra_en_alhambra(){
        val alhambra= LatLng(37.1760783, -3.5881413)
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(alhambra, 16F))

    }

    private fun anadeMarcador(coord:LatLng, nombre:String){
        val coordenadas = coord;
        val marcador = MarkerOptions().position(coordenadas).title(nombre);
        mapa.addMarker(marcador)
    }

    private fun anadirMarcadoresLeyendas(leyendas:MutableList<Leyenda>){
        for(leyenda in leyendas){
            val coordenada = LatLng(leyenda.Lat,leyenda.Long);
            anadeMarcador(coordenada, leyenda.nombre)
        }
    }
}