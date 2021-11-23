package com.g2.ago

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_parrafo.*

class ParrafoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parrafo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnComprobarParrafo.setOnClickListener{
            if(rbtnOP2.isChecked){
                val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.ondo)
                Toast.makeText(requireContext(), "Bien echo", Toast.LENGTH_SHORT).show()
                mp!!.start()
            }else{
                val mp:MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.ondo)
                Toast.makeText(requireContext(), "Otra vez sera", Toast.LENGTH_SHORT).show()
                mp!!.start()
            }
        }
    }
}