package com.g2.ago

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import kotlinx.android.synthetic.main.tabla_ranking.view.*

class ProfesAdapter() /*(private var partidas: List<Partidas>, context: Context) : RecyclerView.Adapter<ProfesAdapter.ViewHolder>() {
    private  var context1: Context=context
    private val fb = FirebaseFirestore.getInstance()
    private var bd:Base_de_Datos = Base_de_Datos(context, "bd", null, 1)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.tabla_ranking, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var Juegos: MutableList<Partidas> = mutableListOf()

        fb.collection("Players")
            .get()
            .addOnSuccessListener { resultado ->
                for(documento in resultado){
                    bd.profes()
                    bd.insertar(documento.id, documento.data.toString())
                }
                partidas = bd.Cargar()
        }

        holder.bind(partidas)
    }

    override fun getItemCount(): Int {
        return partidas.size
    }
    //viewholder del ranking
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bind(partida: List<Partidas>){
            fb.collection("Players")
                .get()
                .addOnSuccessListener { resultado ->
                    for(documento in resultado){
                        itemView.txtNick.text = documento.id
                        itemView.txtPunto.text = "${documento.data}/8"
                    }
                }

        }
    }

}*/

