package com.acl.leyendasdelaalhambra

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_leyenda_detalles.*

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

        nombreText.text = leyenda.nombre
        descripcionText.text = leyenda.descripcion
        Glide.with(this).load(leyenda.imagen).into(imagen_leyenda);

        val boton = view.findViewById<Button>(R.id.boton_localizacion)

        //Botón de la ubicación
        boton.setOnClickListener {
            (activity as MainActivity).onBotonLocalizacionSelected(leyenda)
            Toast.makeText(context,leyenda.nombre,Toast.LENGTH_SHORT).show()
        }

        return view
    }

}