package com.g2.ago

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.g2.ago.databinding.FragmentMapsBinding
import com.g2.ago.databinding.FragmentRankingBinding
import com.g2.ago.databinding.FragmentRankingProfesBinding
import kotlinx.android.synthetic.main.fragment_ranking.*
import kotlinx.android.synthetic.main.tabla_ranking.view.*
import java.io.Serializable

class RankingProfesFragment : Fragment()/* {
    //Con el siguiente codigo generamos el ranking
    lateinit var adapter: ProfesAdapter
    private var title: String= ""
    lateinit var binding: FragmentRankingProfesBinding
    var Activityppal: Comunicador?=null
    private  var partidas=ArrayList<Partidas>()

    companion object{
        private val TITLE = "TITLE"
        private val PLAYS = "PLAYS"

        fun newInstance(title: String, partidas: ArrayList<Partidas>): RankingFragment{
            val fragment = RankingFragment()
            val args = Bundle()
            args.putString(TITLE, title)
            args.putSerializable(PLAYS, partidas as Serializable)
            fragment.arguments = args
            return fragment
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = arguments?.getString(TITLE).toString()
            partidas = arguments?.getSerializable(PLAYS) as ArrayList<Partidas>
        }


    }



    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRankingProfesBinding.inflate(layoutInflater)
        return binding.root
       return inflater.inflate(R.layout.fragment_ranking_profes, container, false)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Base_de_Datos(requireContext(), "bd", null, 1)
        configureView()
        //Al pulsar el boton se comprueba que el campo de texto no esta vacio y que no esta repetido y si se dan ambas condiciones nos deja crear la partida
        btnApodo.setOnClickListener{
            if (txtApodo.text.toString().trim().equals("")){
                Toast.makeText(requireContext(), "Introduce un apodo por favor", Toast.LENGTH_SHORT).show()
            }else if (!db.Cargar_jugadores(txtApodo.text.toString().toLowerCase())){
                Toast.makeText(requireContext(), "Esta partida ya existe", Toast.LENGTH_SHORT).show()
            }else {
                Sharedapp.users.user = txtApodo.text.toString()
                Sharedapp.puntopartida.Partida = "0"
                Sharedapp.puntojuego.Juego = "1"
                ContextCompat.startActivity(requireContext(), Intent(requireContext(), JuegoActivity::class.java), null)
                db.insertar(txtApodo.text.toString(), "0")

            }
        }
    }

    private fun configureView(){
        setUpRecyclerView(db.Cargar())
    }*/
    private fun setUpRecyclerView(partyhard : ArrayList<Partidas>) {
        adapter = ProfesAdapter(partyhard, context = requireContext())
        rankingLyout.setHasFixedSize(true)
        rankingLyout.layoutManager = LinearLayoutManager(activity)
        rankingLyout.adapter = adapter
    }
}*/