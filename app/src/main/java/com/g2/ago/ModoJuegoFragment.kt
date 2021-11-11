package com.g2.ago

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.g2.ago.databinding.FragmentModoJuegoBinding

class ModoJuegoFragment : Fragment() {

    lateinit var binding: FragmentModoJuegoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentModoJuegoBinding.inflate(layoutInflater)


        return binding.root
    }

}