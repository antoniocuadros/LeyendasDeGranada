package com.acl.leyendasdelaalhambra

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_mapa.*

class MapaFragment : Fragment(), GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {
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
        activarUbicacionTiempoReal()
        centra_en_alhambra();

        val accesoDatos = AccesoDatos()
        val leyendas = accesoDatos.obtenerLeyendas()
        anadirMarcadoresLeyendas(leyendas)

        mapa.setOnInfoWindowClickListener(this)
    }

    private fun centra_en_alhambra(){
        val alhambra= LatLng(37.1760783, -3.5881413)
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(alhambra, 16F))

    }

    private fun anadeMarcador(leyenda:Leyenda){
        val coordenadas = LatLng(leyenda.Lat,leyenda.Long);


        val marcador = mapa.addMarker(MarkerOptions().position(coordenadas).title(leyenda.nombre).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)))
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