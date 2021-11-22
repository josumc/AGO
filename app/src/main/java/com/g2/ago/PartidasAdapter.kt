package com.g2.ago

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import kotlinx.android.synthetic.main.tabla_ranking.view.*

class PartidasAdapter (private val partidas: List<Partidas>, context: Context) : RecyclerView.Adapter<PartidasAdapter.ViewHolder>() {
    private  var context1: Context=context
    private var bd:Base_de_Datos = Base_de_Datos(context, "bd", null, 1)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidasAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.tabla_ranking, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = bd.Cargar().get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return bd.Cargar().size
    }
    //viewholder del ranking
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bind(partida:Partidas){
            itemView.txtNick.text = partida.Nickname
            itemView.txtPunto.text = partida.PuntoJuego+"/8"

            itemView.setOnClickListener{
                Sharedapp.users.user = itemView.txtNick.text.toString()
                startActivity(context1, Intent(context1, JuegoActivity::class.java), null)


            }
        }
    }
}