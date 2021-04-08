package com.acl.leyendasdelaalhambra

import android.content.Intent
import android.graphics.drawable.Icon
import android.media.Image
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_leyenda_detalles.*
import me.relex.circleindicator.CircleIndicator3

class LeyendaDetallesFragment : Fragment() {
    private val argumentos_recibidos_leyendas: LeyendaDetallesFragmentArgs by navArgs()
    private var reproductor: MediaPlayer? = null
    private var boton_sonido:ImageButton? = boton_play
    private var boton_sonido_stop:ImageButton? = boton_stop
    lateinit var nombreText:TextView
    lateinit var descripcionText:TextView
    lateinit var imagen_leyenda:ImageView
    lateinit var derechos:TextView
    lateinit var boton_localizacion:Button
    lateinit var boton_sitio:Button
    lateinit var viewpager_imagenes:ViewPager2
    lateinit var indicador_pagina_imagen_slider:CircleIndicator3
    lateinit var menu:View
    lateinit var leyenda:Leyenda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this){
            (activity as MainActivity).de_detalles_a_leyendas()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_leyenda_detalles, container, false)

        inicializa_vistas(view)

        leyenda = argumentos_recibidos_leyendas.leyenda


        menu.visibility = View.VISIBLE

        estableceDatosLeyenda()

        return view
    }

    private fun estableceDatosLeyenda(){

        establece_datos_principales()

        estableceSliderImagenes()

        establece_boton_localización()

        establece_boton_sitio()

        establece_texto_derechos()

        establece_botones_sonido()
    }

    private fun establece_datos_principales(){
        nombreText.text = leyenda.nombre
        descripcionText.text = leyenda.descripcion
        derechos.text = leyenda.fuente.take(50)

        derechos.setMovementMethod(LinkMovementMethod.getInstance())

        Glide.with(this).load(leyenda.imagen).into(imagen_leyenda);
    }

    private fun inicializa_vistas(view:View){
        nombreText = view.findViewById<TextView>(R.id.nombre_leyenda_detalles)
        descripcionText = view.findViewById<TextView>(R.id.descripcion_leyenda_detalles)
        imagen_leyenda = view.findViewById<ImageView>(R.id.imagen_leyenda_detalles)
        derechos = view.findViewById<TextView>(R.id.derechos_leyenda)
        boton_sonido = view.findViewById<ImageButton>(R.id.boton_play)
        boton_sonido_stop = view.findViewById<ImageButton>(R.id.boton_stop)
        viewpager_imagenes = view.findViewById<ViewPager2>(R.id.viewpager_imagenes)
        indicador_pagina_imagen_slider = view.findViewById<CircleIndicator3>(R.id.indicador_slider)
        boton_localizacion = view.findViewById<Button>(R.id.boton_localizacion)
        boton_sitio= view.findViewById<Button>(R.id.boton_sitio_web)
        menu = requireActivity().findViewById<View>(R.id.menu_inferior)
    }
    private fun estableceSliderImagenes(){
        //Slider de imágenes
        var imagen_list = leyenda.imagenes_adicionales


        //Si no hay imágenes no dejamos el hueco vacío, lo eliminamos
        if(imagen_list.size == 0){
            viewpager_imagenes.visibility = View.GONE
            indicador_pagina_imagen_slider.visibility = View.GONE
        }
        viewpager_imagenes.adapter = SliderImagenesAdapter(imagen_list)
        viewpager_imagenes.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicador_pagina_imagen_slider.setViewPager(viewpager_imagenes)
    }

    private fun establece_boton_localización(){
        //Botón de la ubicación

        boton_localizacion.setOnClickListener {
            (activity as MainActivity).onBotonLocalizacionSelected(leyenda)
        }
    }

    private fun establece_boton_sitio(){
        if(leyenda.sitio_web == ""){
            boton_sitio.visibility =  View.GONE
        }

        boton_sitio.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(leyenda.sitio_web))
            getActivity()?.startActivity(i)
        }
    }

    private fun establece_texto_derechos(){
        derechos.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(leyenda.fuente))
            getActivity()?.startActivity(i)
        }
    }

    private fun establece_botones_sonido(){
        boton_sonido?.setOnClickListener{

            if(reproductor == null){
                reproductor = MediaPlayer.create(context, resources.getIdentifier(leyenda.id_musica, "raw", context?.getPackageName()))
            }

            if(reproductor != null && reproductor?.isPlaying!!){ //Pausar
                reproductor?.pause()

                boton_sonido?.setImageResource(R.drawable.play)

            }
            else{ //Reanudar
                reproductor?.setOnCompletionListener {
                    it.reset()
                    it.release()
                    boton_sonido?.setImageResource(R.drawable.play)
                }

                reproductor?.start()
                boton_sonido?.setImageResource(R.drawable.pause)

                boton_sonido_stop?.visibility = View.VISIBLE
            }


        }

        //Boton Stop
        boton_sonido_stop?.setOnClickListener{
            reproductor?.reset()
            reproductor?.release()
            reproductor = null
            boton_sonido_stop?.visibility = View.GONE
            boton_sonido?.setImageResource(R.drawable.play)
        }
    }

    override fun onPause() {
        super.onPause()

        reproductor?.stop()
        reproductor?.reset()
        reproductor?.release()
        reproductor = null
        boton_sonido?.setImageResource(R.drawable.play)
    }

}