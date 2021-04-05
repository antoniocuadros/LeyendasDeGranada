package com.acl.leyendasdelaalhambra

import android.content.Context
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
            changeLocale("en")
        }

        botonespanol.setOnClickListener{
            changeLocale("es")
        }


        return view
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