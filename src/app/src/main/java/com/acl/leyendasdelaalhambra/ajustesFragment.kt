package com.acl.leyendasdelaalhambra

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


class ajustesFragment : Fragment() {
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
            var locale = Locale("en")
            val configuracion = Configuration()
            configuracion.setLocale(locale)

            Locale.setDefault(locale)
            context?.createConfigurationContext(configuracion)
            getActivity()?.recreate()
            resources.updateConfiguration(configuracion, resources.getDisplayMetrics());
        }

        botonespanol.setOnClickListener{
            var locale = Locale("es")
            val configuracion = Configuration()
            configuracion.setLocale(Locale("es"))

            Locale.setDefault(locale)
            context?.createConfigurationContext(configuracion)
            getActivity()?.recreate()

            val resources: Resources = requireContext().resources
            resources.updateConfiguration(configuracion, resources.getDisplayMetrics());
        }


        return view
    }

    fun checkCurrentLanguage(){
        if(Locale.getDefault().getLanguage() == "es"){
            botonespanol.isChecked = true
        }
        else{
            botoningles.isChecked = true
        }
    }

}