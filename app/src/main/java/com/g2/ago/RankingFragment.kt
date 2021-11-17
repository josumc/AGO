package com.g2.ago

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_ranking.*
import kotlinx.android.synthetic.main.tabla_ranking.*
import java.io.Serializable

class RankingFragment : Fragment() {
    lateinit var adapter: PartidasAdapter
    private var title: String= ""
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
        return inflater.inflate(R.layout.fragment_ranking, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureView()
    }

    private fun configureView(){
       // titleNick.text = "1"
       // titlePunto.text = "1"
        partidas.add(Partidas("Mikel","3/8"))
        partidas.add(Partidas("Gonzalo","4/8"))
        partidas.add(Partidas("Aitor","1/8"))
        partidas.add(Partidas("Josu","6/8"))
        partidas.add(Partidas("Iker","5/8"))
        partidas.add(Partidas("Jaime","8/8"))

        setUpRecyclerView(partidas)
    }
    private fun setUpRecyclerView(partyhard : ArrayList<Partidas>){
        adapter= PartidasAdapter(partyhard)
        rankingLyout.setHasFixedSize(true)
        rankingLyout.layoutManager = LinearLayoutManager(activity)
        rankingLyout.adapter=adapter
    }
}
