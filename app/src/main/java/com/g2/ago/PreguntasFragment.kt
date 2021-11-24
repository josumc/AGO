package com.g2.ago

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_preguntas.*

class PreguntasFragment : Fragment() {
    var check:String= "ok"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preguntas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnValidarPreguntas.setOnClickListener{
            if (!txtRespuesta1.text.toString().equals("3")) {
                txtPregunta1.setTextColor(Color.RED)
                txtRespuesta1.text = null
                txtRespuesta2.text = null
            }
            if (!txtRespuesta2.text.toString().equals("3")) {
                txtPregunta2.setTextColor(Color.RED)
                txtRespuesta1.text = null
                txtRespuesta2.text = null
            }
            if(check.equals("ok")){
                    val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.ondo)
                    Toast.makeText(requireContext(), "Bien echo", Toast.LENGTH_SHORT).show()
                    mp!!.start()
                }else{

                val mp:MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.ondo)
                Toast.makeText(requireContext(), "Alguna respuesta esta mal", Toast.LENGTH_SHORT).show()
                mp!!.start()
                check="ok"
            }
        }
    }
}