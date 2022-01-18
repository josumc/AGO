package com.g2.ago

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.FragmentModoJuegoBinding

class ModoJuegoFragment : Fragment() {

    lateinit var binding: FragmentModoJuegoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentModoJuegoBinding.inflate(layoutInflater)

        binding.btnGuiado.setOnClickListener(){
            Sharedapp.modolibre.modo = false
            Sharedapp.puntopartida.Partida="1"
            startActivity(Intent(requireContext(), JuegoActivity::class.java))
            requireActivity().finish()
        }

        binding.btnLibre.setOnClickListener(){
            Sharedapp.modolibre.modo = true
            startActivity(Intent(requireContext(), JuegoActivity::class.java))
            requireActivity().finish()
        }

        return binding.root
    }

}