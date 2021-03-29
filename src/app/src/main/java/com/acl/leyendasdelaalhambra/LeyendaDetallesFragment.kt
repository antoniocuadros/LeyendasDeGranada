package com.acl.leyendasdelaalhambra

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_leyenda_detalles.*
import me.relex.circleindicator.CircleIndicator3

class LeyendaDetallesFragment : Fragment() {
    private val argumentos_recibidos_leyendas: LeyendaDetallesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_leyenda_detalles, container, false)

        val leyenda = argumentos_recibidos_leyendas.leyenda

        val nombreText = view.findViewById<TextView>(R.id.nombre_leyenda_detalles)
        val descripcionText = view.findViewById<TextView>(R.id.descripcion_leyenda_detalles)
        val imagen_leyenda = view.findViewById<ImageView>(R.id.imagen_leyenda_detalles)
        val derechos = view.findViewById<TextView>(R.id.derechos_leyenda)

        nombreText.text = leyenda.nombre
        descripcionText.text = leyenda.descripcion
        derechos.text = leyenda.fuente.take(50)

        derechos.setMovementMethod(LinkMovementMethod.getInstance())

        Glide.with(this).load(leyenda.imagen).into(imagen_leyenda);

        val boton = view.findViewById<Button>(R.id.boton_localizacion)

        //Botón de la ubicación
        boton.setOnClickListener {
            (activity as MainActivity).onBotonLocalizacionSelected(leyenda)
        }

        //Texto derechos
        derechos.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(leyenda.fuente))
            getActivity()?.startActivity(i)
        }

        //Slider de imágenes
        var imagen_list = mutableListOf<String>(leyenda.imagen)
        var viewpager_imagenes = view.findViewById<ViewPager2>(R.id.viewpager_imagenes)

        viewpager_imagenes.adapter = SliderImagenesAdapter(imagen_list)
        viewpager_imagenes.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicador_pagina_imagen_slider = view.findViewById<CircleIndicator3>(R.id.indicador_slider)
        indicador_pagina_imagen_slider.setViewPager(viewpager_imagenes)


        return view
    }

}