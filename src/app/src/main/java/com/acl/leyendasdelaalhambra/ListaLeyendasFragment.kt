package com.acl.leyendasdelaalhambra

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaLeyendasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_lista_leyendas, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler_leyendas)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = LeyendaAdapter()
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            (activity as MainActivity).onLeyendaSelected(it)
        }

        val leyendaList = AccesoDatos()


        adapter.submitList(leyendaList.obtenerLeyendas())
        return view
    }
}