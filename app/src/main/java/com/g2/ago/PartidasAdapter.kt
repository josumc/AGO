package com.g2.ago

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import kotlinx.android.synthetic.main.tabla_ranking.view.*

class PartidasAdapter (private val partidas: List<Partidas>) : RecyclerView.Adapter<PartidasAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidasAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.tabla_ranking, parent, false))
    }

    override fun onBindViewHolder(holder: PartidasAdapter.ViewHolder, position: Int) {
        val item = partidas[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return partidas.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(partida:Partidas){
            itemView.txtNick.text = partida.Nickname
            itemView.txtPunto.text = partida.PuntoJuego
        }
    }

}