package com.g2.ago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.g2.ago.databinding.FragmentRankingProfesBinding
import kotlinx.android.synthetic.main.fragment_ranking_profes.*

class RankingProfesFragment : Fragment() {
    //Con el siguiente codigo generamos el ranking
    lateinit var adapter: ProfesAdapter
    lateinit var binding: FragmentRankingProfesBinding
    private lateinit var db:Base_de_Datos

    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRankingProfesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Base_de_Datos(requireContext(), "bd", null, 1)
        configureView()
    }

    private fun configureView(){
        setUpRecyclerView(db.Cargar_ranking())
    }
    private fun setUpRecyclerView(partyhard : ArrayList<Partidas>){
        adapter= ProfesAdapter(partyhard, context = requireContext())
        ProfesLyout.setHasFixedSize(true)
        ProfesLyout.layoutManager = LinearLayoutManager(activity)
        ProfesLyout.adapter=adapter
    }

}