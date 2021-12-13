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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import kotlinx.android.synthetic.main.tabla_ranking.view.*

class ProfesAdapter (private val partidas: List<Partidas>, context: Context) : RecyclerView.Adapter<ProfesAdapter.ViewHolder>() {
    //private  var context1: Context=context
    lateinit var db: FirebaseFirestore
    private var bd:Base_de_Datos = Base_de_Datos(context, "bd", null, 1)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.tabla_ranking, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Cargar eso en un metodo y hacerlo al cargar la clase

                val item = bd.Cargar_ranking().get(position)
                holder.bind(item)
    }

    override fun getItemCount(): Int {
        return bd.Cargar_ranking().size
    }
    //viewholder del ranking
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bind(partida:Partidas){
            itemView.txtNick.text = partida.Nickname
            val puntopartida = "${partida.PuntoPartida}/8"
            itemView.txtPunto.text = puntopartida
        }
    }

   /* override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        bd.profes()
    }*/
}