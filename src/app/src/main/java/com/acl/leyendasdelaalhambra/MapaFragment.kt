package com.acl.leyendasdelaalhambra

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MapaFragment : Fragment(), GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {
    private lateinit var mapa: GoogleMap;
    private val argumentos_detalles:MapaFragmentArgs by navArgs()
    private lateinit var boton_todas:FloatingActionButton

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

        boton_todas = vista.findViewById<FloatingActionButton>(R.id.boton_flotante_todas)

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
        val alhambra= LatLng(37.1760783, -3.5881413)

        mapa = _mapa;
        activarUbicacionTiempoReal()
        centraMapa(alhambra, 16F);

        val accesoDatos = AccesoDatos(context)
        val leyendas = accesoDatos.obtenerLeyendas()
        var polyline1: Polyline = mapa.addPolyline(PolylineOptions())



        if(argumentos_detalles.leyenda == null){
            if(argumentos_detalles.recorrido != null){ //venimos de recorridos
                val recorrido:Recorrido = argumentos_detalles.recorrido!!
                //Mostramos los marcadores de las leyendas asociadas al recorrido
                anadirMarcadoresLeyendas(recorrido.leyendas)

                //Generamos las polilineas, para que no sea un caos se ha establecido un orden, vamos a ordenarlas.
                val leyendas_ordenadas = recorrido.leyendas.sortedBy { it.orden }
                var coordenadas:MutableList<LatLng> = mutableListOf()

                for(leyend in leyendas_ordenadas) {
                    coordenadas.add(LatLng(leyend.Lat, leyend.Long))
                }
                polyline1= mapa.addPolyline(PolylineOptions()
                        .clickable(true).color(R.color.Rojo)
                        .addAll(
                            coordenadas
                        )
                )

                boton_todas.show()

                //centramos la vista en el recorrido
                centraMapa(recorrido.LatitudLongitud, recorrido.zoom)

            }
            else{//no venimos de los detalles
                anadirMarcadoresLeyendas(leyendas)
            }
        }
        else{//venimos de la pestaña detalles
            anadeMarcador(argumentos_detalles.leyenda!!)
            centraMapa(LatLng(argumentos_detalles.leyenda!!.Lat, argumentos_detalles.leyenda!!.Long), 19F)

            boton_todas.show()
        }

        //Listener del floating button
        boton_todas.setOnClickListener{
            anadirMarcadoresLeyendas(leyendas)
            boton_todas.hide()
            polyline1.remove()
            //centramos de nuevo en la Alhambra
            centraMapa(LatLng(37.1760783,-3.5881413), 16F)

        }

        mapa.setOnInfoWindowClickListener(this)
    }

    private fun centraMapa(coords:LatLng, zoom:Float){
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, zoom))
    }

    private fun anadeMarcador(leyenda:Leyenda){
        val coordenadas = LatLng(leyenda.Lat,leyenda.Long);


        val marcador = mapa.addMarker(MarkerOptions().position(coordenadas).title(leyenda.nombre).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).snippet(leyenda.ubicacion))
        marcador.tag = leyenda
    }

    private fun anadirMarcadoresLeyendas(leyendas:MutableList<Leyenda>){
        for(leyenda in leyendas){
            anadeMarcador(leyenda)
        }
    }

    private fun compruebaPermisos(context:Context){
        //Desde android M necesitamos pedir explicitamente el permiso de ubicación
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){


            //El permiso de ubicación ya está otorgado
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                //OBTENEMOS LA UBICACIÓN
            }
            else{ //el permiso de ubicación no está otorgado
                //pedimos el permiso, pasamos el permiso y un código cualquiera
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 200)
            }
        }
        else{ //inferior a android M, obtenemos la ubicación directamente
            //OBTENEMOS LA UBICACIÓN
        }
    }

    ///////////////////////////////////////////
    //
    //  PERMISOS Y UBICACIÓN EN TIEMPO REAL
    //
    ///////////////////////////////////////////
    private fun activarUbicacionTiempoReal(){
        //Si entra en el if es porque ya ha aceptado los permisos
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mapa.isMyLocationEnabled = true
        }
        else{ //aun no ha aceptado los permisos, tenemos que pedirle los permisos
            //ya le hemos pedido los permisos con anterioridad pero los rechazó
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity as Activity, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(context, "Es necesario otorgar permisos de localización", Toast.LENGTH_LONG).show()
            }
            else{ //Por primera vez le pedimos los permisos
                ActivityCompat.requestPermissions(activity as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 200)
            }
        }
    }

    //Ahora tenemos que capturar la respuesta cuando el usuario acepte los permisos
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 100){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){  //Ha aceptado el permiso
                mapa.isMyLocationEnabled = true
            }else{//se ha rechazado el permiso de ubicación
                Toast.makeText(context, "Es necesario otorgar permisos de localización", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onInfoWindowClick(marcador: Marker?) {
        var leyenda = marcador?.tag as Leyenda
        (activity as MainActivity).onMarcadorSelected(leyenda)
    }
}