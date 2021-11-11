package com.g2.ago

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_log.*

class RankingFragment : Fragment() {
override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_ranking, container, false)
        setupNavigation(root)
        return root
    }
    private fun setupNavigation(root: View){
        val botonb = root.findViewById<Button>(R.id.button)
        botonb.setOnClickListener{view : View ->
            val navControler = findNavController()
            view.findNavController().navigate(R.id.action_rankingFragment_to_logFragment)
        }
    }
}