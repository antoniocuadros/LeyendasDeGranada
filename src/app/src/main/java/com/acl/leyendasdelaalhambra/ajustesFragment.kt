package com.acl.leyendasdelaalhambra

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import java.util.*

/*
    Este fragmento dota de funcionalidad a la vista de los ajustes donde podemos gestionar
    el idioma, pudiendo elegir entre español o inglés.
 */
class ajustesFragment : Fragment() {
    /*
    ATRIBUTOS DE LA CLASE ajustesFragment:
        -> radiobuttongroup: Vista de tipo RadioGroup que agrupa los botones de idioma.
        -> botoningles: Vista de tipo RadioButton que contiene la opción de idioma en inglés.
        -> botonespanol: Vista de tipo RadioButton que contiene la opción de idioma en español.
     */
    lateinit var radiobuttongroup:RadioGroup
    lateinit var botoningles:RadioButton
    lateinit var botonespanol:RadioButton

    /*
    El método onCreate de cualquier Fragment es llamado cuando se crea inicialmente el fragmento,
    se llama al método onCreate de la clase superior, Fragment para crear el fragmento.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /*
    El método onCreateView de un fragmento crea y devuelve la jerarquía de la vista asociada con el
    fragmento.
    Adicionalmente de forma específica a este fragmento se realizan los siguientes pasos:
        -> 1) Se inicializan los atributos de la clase asociados a las vistas.
        -> 2) Se llama al método checkCurrentLanguage que en función del lenguaje definido en la aplicación
              previamente o del propio dispositivo se marca el radioButton correspondiente, por ejemplo,
              si el idioma de la app está en español al entrar a la pantalla de ajustes se muestra como marcado
              el idioma español ya que está seleccionado.
        -> 3) Se añaden los clickListener para el radioButton tanto de español como de inglés,
              de tal forma que tienen el siguiente comportamiento:
                -> Se llama al método obtenerLenguajeActual que comprueba en que idioma está la aplicación
                   ahora mismo, de tal forma que si está en el idioma pulsado no hace nada, si se cambia
                   de idioma entonces se llama al método changeLocale que cambia la configuración de idioma
                   de la aplicación y reinicia la actividad para que incluso en la propia pantalla de idioma
                   se refleje el cambio de idioma al instante.
                   Adicionalmente se llama al método guardaPersistente para que haciendo uso de sharedPreferences
                   se guarde persistentemente el idioma de la aplicación como un string para tenerlo en cuenta
                   en reinicios de la aplicación.
        -> 4) Se devuelve la vista

     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ajustes, container, false)

        // Paso 1)
        radiobuttongroup = view.findViewById(R.id.radiogroup)
        botonespanol = view.findViewById(R.id.botonespanol)
        botoningles = view.findViewById(R.id.botoningles)

        // Paso 2)
        checkCurrentLanguage()

        // Paso 3)
        botoningles.setOnClickListener{
            if(obtenerLenguajeActual() != "en"){
                changeLocale("en")
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                guardaPersistente("lang", "en")
            }
            botonespanol.isChecked = false

        }

        botonespanol.setOnClickListener{
            if(obtenerLenguajeActual() != "es") {
                changeLocale("es")
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                guardaPersistente("lang", "es")
            }
            botoningles.isChecked = false
        }

        // Paso 4)
        return view
    }

    /*
    Este método recibe como parámetros dos strings a modo de clave valor para almacenarlos de forma
    persistente haciendo uso de sharedPreferences:
        -> atributo: actúa como clave
        -> valor: actúa como valor
    De esta forma se crea un archivo llamado 'ajustes' haciendo uso de sharedPreferences donde se
    guardará la pareja atributo -> valor.
    El almacenamiento persistente es utilizado a la hora de acceder a los datos y recuperar la información
    en un determinado idioma.
     */
    private fun guardaPersistente(atributo:String, valor:String){
        var sharedPreferences:SharedPreferences = requireContext().getSharedPreferences("ajustes", 0)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putString(atributo, valor)
        sharedPreferencesEditor.commit()
    }

    /*
    Este método haciendo uso de sharedPreferences recupera el valor asociado a la clave almacenada
    'lang' y la devuelve como un string.
     */
    private fun obtenerLenguajeActual():String{
        var sharedPreferences: SharedPreferences = requireContext().applicationContext.getSharedPreferences("ajustes",0)
        var lang = sharedPreferences.getString("lang", Locale.getDefault().getLanguage())
        return lang.toString()
    }

    /*
    Este método pasándole un id, que es del tipo "es", "en", ... cambia la configuración del idioma
    de la aplicación. Una vez cambiado el idioma reinicia la actividad para aplicar de forma
    inmediata el cambio de idioma y se reflejen los cambios sin necesidad de cambiar de fragmento para
    empezar a ver los cambios de idioma
     */
    private fun changeLocale(id:String){
        var locale = Locale(id)
        val configuracion = Configuration()
        val resources: Resources = requireContext().resources

        configuracion.setLocale(Locale(id))
        Locale.setDefault(locale)
        resources.updateConfiguration(configuracion, resources.getDisplayMetrics())
        getActivity()?.recreate()
    }

    /*
    Este método comprueba la configuración local de la aplicación y se encarga de marcar como
    'checked' el radio button correspondiente. Por defecto inglés si no es español.
     */
    private fun checkCurrentLanguage(){
        if(Locale.getDefault().getLanguage() == "es"){
            botonespanol.isChecked = true
        }
        else{
            botoningles.isChecked = true
        }
    }

}