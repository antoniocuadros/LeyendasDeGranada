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
        -> radiobuttongroup:
        -> botoningles:
        -> botonespanol:
     */
    lateinit var radiobuttongroup:RadioGroup
    lateinit var botoningles:RadioButton
    lateinit var botonespanol:RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ajustes, container, false)

        radiobuttongroup = view.findViewById(R.id.radiogroup)
        botonespanol = view.findViewById(R.id.botonespanol)
        botoningles = view.findViewById(R.id.botoningles)

        checkCurrentLanguage()

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


        return view
    }

    private fun guardaPersistente(atributo:String, valor:String){
        var sharedPreferences:SharedPreferences = requireContext().getSharedPreferences("ajustes", 0)
        val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        sharedPreferencesEditor.putString(atributo, valor)
        sharedPreferencesEditor.commit()
    }

    private fun obtenerLenguajeActual():String{
        var sharedPreferences: SharedPreferences = requireContext().applicationContext.getSharedPreferences("ajustes",0)
        var lang = sharedPreferences.getString("lang", Locale.getDefault().getLanguage())
        return lang.toString()
    }
    private fun changeLocale(id:String){
        var locale = Locale(id)
        val configuracion = Configuration()
        val resources: Resources = requireContext().resources

        configuracion.setLocale(Locale(id))
        Locale.setDefault(locale)
        resources.updateConfiguration(configuracion, resources.getDisplayMetrics())
        getActivity()?.recreate()
    }

    private fun checkCurrentLanguage(){
        if(Locale.getDefault().getLanguage() == "es"){
            botonespanol.isChecked = true
        }
        else{
            botoningles.isChecked = true
        }
    }

}