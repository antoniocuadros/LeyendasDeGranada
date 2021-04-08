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

/*
    Este fragmento dota de funcionalidad a la vista de los detalles de una leyenda donde podemos
    consultar todos los datos de una leyenda como puede ser una foto principal, título, descripción,
    fuente de donde se ha obtenido la información, otras imágenes, escuchar la leyenda, obtener
    la ubicación y la información del sitio.
 */
class LeyendaDetallesFragment : Fragment() {
    /*
    ATRIBUTOS DE LA CLASE LeyendaDetallesFragment:
        -> argumentos_recibidos_leyendas: Argumento recibido de la navegación desde la lista de leyendas
                                          a este fragmento, recibe la leyenda que ha sido pulsada.
        -> reproductor: objeto de tipo MediaPlayer que representa el reproductor de la descripción
                        de leyendas.
        -> boton_sonido: Vista de tipo ImageButton que representa el botón de reproducir y pausar.
        -> boton_sonido_stop: Vista de tipo ImageButton que representa el botón de resetear la reproducción
                              de la descripción de la leyenda.
        -> nombreText: Vista de tipo TextView que contendrá el título de la leyenda.
        -> descripcionText: Vista de tipo TextView que contendrá la descripción de la leyenda.
        -> imagen_leyenda: Vista de tipo ImageView que contiene la imagen principal de la leyenda.
        -> derechos: Vista de tipo TextView que contiene la URL de la página donde se ha extraido la leyenda.
        -> boton_localizacion: Vista de tipo Button que contiene un botón que redirige a la localización en el mapa.
        -> boton_sitio: Vista de tipo Button que redirige a la página web del sitio donde transcurre la leyenda (opcional).
        -> viewpager_imagenes: Vista de tipo ViewPager2 que contiene un slider de imágenes relacionadas con la leyenda (opcional).
        -> indicador_pagina_imagen_slider: Vista de tipo CircleIndicator3 que contiene un indicador del número de imágenes que contiene la leyenda.
        -> menu: Vista de tipo View que contiene el menú inferior de la aplicación
        -> leyenda: objeto de tipo Leyenda que representa la leyenda a mostrar.
     */
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

    /*
    El método onCreate de cualquier Fragment es llamado cuando se crea inicialmente el fragmento,
    se llama al método onCreate de la clase superior, Fragment para crear el fragmento.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this){
            (activity as MainActivity).de_detalles_a_leyendas()
        }

    }
    /*
    El método onCreateView de un fragmento crea y devuelve la jerarquía de la vista asociada con el
    fragmento.
    Adicionalmente de forma específica a este fragmento se realizan los siguientes pasos:
        -> 1) Se llama al método inicializa_vistas, que se encarga de asignarle su correspondiente
              vista a cada atributo de la clase que sea una vista y poder trabajar sobre ellos a
              continuación.
        -> 2) Se asigna a la variable leyenda la leyenda recibida de la navegación entre fragmentos.
        -> 3) Se hace visible el menú inferior que se desabilitó en la primera pantalla de bienvenida.
        -> 4) Se asigna un valor textual, imagen, listeners... a los distintos elementos de la vista asociada
              llamando al método estableceDatosLeyenda que se detalla más adelante.
        -> 5) Se devuelve la vista.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_leyenda_detalles, container, false)

        // Paso 1)
        inicializa_vistas(view)

        // Paso 2)
        leyenda = argumentos_recibidos_leyendas.leyenda

        // Paso 3)
        menu.visibility = View.VISIBLE

        // Paso 4)
        estableceDatosLeyenda()

        // Paso 5)
        return view
    }

    /*
    Este método se encarga de llamar a otros métodos con el objetivo de dotar de contenido a la vista
    asociada a este fragmento. Para ello llama a los métodos:
        -> establece_datos_principales: Se encarga de establecer el título, imagen principal, descripción
                                        y el texto de los derechos.
        -> estableceSliderImagenes: Se encarga de dotar de contenido el slider de imágenes en caso de que
                                    haya imágenes para el slider.
        -> establece_boton_localización: Se encarga de establecer el listener para el botón de localización.
        -> establece_boton_sitio: Se encarga de gestionar el intent que redirige a internet cuando se pulsa el botón del sitio.
        -> establece_texto_derechos: Se encarga de gestionar el intent que redirige a internet cuando se pulsa el botón de la fuente.
        -> establece_botones_sonido: Se encarga de gestionar los diversos botones de reproducción del sonido que describe la leyenda.
     */
    private fun estableceDatosLeyenda(){
        establece_datos_principales()
        estableceSliderImagenes()
        establece_boton_localización()
        establece_boton_sitio()
        establece_texto_derechos()
        establece_botones_sonido()
    }

    /*
    Este método se encarga de añadir los datos de la leyenda a las vistas correspondientes:
        -> Paso 1) Se asigna el título de la leyenda
        -> Paso 2) Se asigna la descripción de la leyenda.
        -> Paso 3) Se asigna la URL de la fuente de donde se ha obtenido la leyenda (cogiendo solo
                   (los 50 primeros caracteres ya que pueden ser URLs muy largas).
        -> Paso 4) Se asigna la portada haciendo uso de Glide para cargarla desde internet.
     */
    private fun establece_datos_principales(){
        // Paso 1)
        nombreText.text = leyenda.nombre

        // Paso 2)
        descripcionText.text = leyenda.descripcion

        // Paso 3)
        derechos.text = leyenda.fuente.take(50)

        // Paso 4)
        //Glide.with(this).load(leyenda.imagen).into(imagen_leyenda);
    }

    /*
    Este método se encarga de enlazar nuestros atributos de la clase con su correspondiente
    vista para posteriormente haciendo uso del resto de métodos asignarles su valor textual, imágenes,
    ...
     */
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

    /*
    Este método se encarga de en primer lugar, obtener de la leyenda las URLs de las imágenes
    que van a ir en el slider de imágenes adicionales. Si no hay imágenes no se muestra, pero si
    hay imágenes que mostrar se define el adaptador pasándole la lista de imágenes para posteriormente
    definir la orientación como horizontal y se termina asignando el viewpager al indicador inferior
    que indica cuantas imágenes hay.
     */
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

    /*
    Este método se encarga de definir el evento de click del ratón del botón de localización,
    haciendo que se redirija al fragment del mapa para mostrar su ubicación.
     */
    private fun establece_boton_localización(){
        //Botón de la ubicación
        boton_localizacion.setOnClickListener {
            (activity as MainActivity).onBotonLocalizacionSelected(leyenda)
        }
    }

    /*
    Este método se encarga de definir el comportamiento del botón de la información del sitio.
    Si se ha definido una URL del sitio entonces se muestra un botón y al pulsarlo se activa un
    intent que nos redirige a nuestro navegador predeterminado para mostrarnos la información.
     */
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

    /*
    Este método se encarga de definir el clickListener de la url de los derechos de la leyenda.
    Al pulsarlo se crea un intent que nos redirige a nuestro navegador predeterminado con el objetivo
    de ver la página de donde se ha extraído la leyenda.
     */
    private fun establece_texto_derechos(){
        derechos.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(leyenda.fuente))
            getActivity()?.startActivity(i)
        }
    }

    /*
    Este método se encarga de definir el comportamiento del botón de reproducir el sonido que describe
    la leyenda. Para ello se define el clickListener de tal forma que hace lo siguiente:
        -> Si se pulsa y no se había reproducido previamente nada se inicia desde el inicio, se cambia el icono
           al de pausa para poder pausar, se activa un nuevo botón para resetear el audio.
        -> Si se pulsa y se estaba reproduciendo, se pausa.
    Se define el comportamiento del botón para resetear el audio, se activa cuando se inicia una reproducción
    y lo pausa y restaura al principio.
     */
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

    /*
    El método onPause de un fragmento se llama cuando un fragmento es pausado, ha sido necesario
    sobrecargarlo para en caso de que se cambie de fragmento y se esté reproduciendo un sonido,
    parar la reproducción y liberar los recursos asociados para evitar sobrecarga en la aplicación.
     */
    override fun onPause() {
        super.onPause()

        reproductor?.stop()
        reproductor?.reset()
        reproductor?.release()
        reproductor = null
        boton_sonido?.setImageResource(R.drawable.play)
    }
}