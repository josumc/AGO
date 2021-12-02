package com.g2.ago

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.g2.ago.databinding.FragmentExplicacionBinding
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ExplicacionFragment : Fragment() {
    lateinit var binding: FragmentExplicacionBinding
    var testua:String=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentExplicacionBinding.inflate(layoutInflater)
        val explicacion=explicaciones()
        binding.textView.typeWrite(this, explicacion, 33L)
        return binding.root
    }
    //Esta funcion se utiliza para cargar las letas del texto a utilizar de una en una
    fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
        this@typeWrite.text = ""
        lifecycleOwner.lifecycleScope.launch {
            repeat(text.length) {
                delay(intervalMs)
                this@typeWrite.text = text.take(it + 1)
                binding.textView.setOnClickListener {
                    lifecycleOwner.lifecycleScope.cancel()
                    binding.textView.text=explicaciones()
                }
            }
        }
    }
    fun explicaciones(): String {
        when(Sharedapp.puntopartida.Partida){
            "0"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.hasiera1audioa)
                        mp!!.start()
                        testua=getString(R.string.hasiera1)
                    }
                    "2"-> {
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.hasiera2audioa)
                        mp!!.start()
                        testua=getString(R.string.hasiera2)
                    }
                }
            }
            "1"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.portua1audioa)
                        mp!!.start()
                        testua=getString(R.string.portua1)
                    }
                    "2"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.portua2audioa)
                        mp!!.start()
                        testua=getString(R.string.portua2)
                    }
                    "3"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.portua3audioa)
                        mp!!.start()
                        testua=getString(R.string.portua3)
                    }
                }
            }
            "2"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.kofradia1audioa)
                        mp!!.start()
                        testua=getString(R.string.kofradia1)
                    }
                    "2"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.kofradia2audioa)
                        mp!!.start()
                        testua=getString(R.string.kofradia2)
                    }
                    "3"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.kofradia3audioa)
                        mp!!.start()
                        testua=getString(R.string.kofradia3)
                    }
                }
            }
            "3"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.sotera1audioa)
                        mp!!.start()
                        testua=getString(R.string.sotera1)
                    }
                    "2"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.sotera2audioa)
                        mp!!.start()
                        testua=getString(R.string.sotera2)
                    }
                    "3"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.sotera3audioa)
                        mp!!.start()
                        testua=getString(R.string.sotera3)
                    }
                    "4"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.sotera4audioa)
                        mp!!.start()
                        testua=getString(R.string.sotera4)
                    }
                }
            }
            "4"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.kioskoa1audioa)
                        mp!!.start()
                        testua=getString(R.string.kioskoa1)
                    }
                    "2"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.kioskoa2audioa)
                        mp!!.start()
                        testua=getString(R.string.kioskoa2)
                    }
                    "3"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.kioskoa3audioa)
                        mp!!.start()
                        testua=getString(R.string.kioskoa3)
                    }
                }
            }
            "5"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.udaletxea1audioa)
                        mp!!.start()
                        testua=getString(R.string.udaletxea1)
                    }
                    "2"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.udaletxea2audioa)
                        mp!!.start()
                        testua=getString(R.string.udaletxea2)
                    }
                    "3"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.udaletxea3audioa)
                        mp!!.start()
                        testua=getString(R.string.udaletxea3)
                    }
                    "4"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.udaletxea4audioa)
                        mp!!.start()
                        testua=getString(R.string.udaletxea3)
                    }
                }
            }
            "6"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.auzoa1audioa)
                        mp!!.start()
                        testua=getString(R.string.auzoa1)
                    }
                    "2"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.auzoa2audioa)
                        mp!!.start()
                        testua=getString(R.string.auzoa2)
                    }
                    "3"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.auzoa3audioa)
                        mp!!.start()
                        testua=getString(R.string.auzoa3)
                    }
                    "4"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.auzoa4audioa)
                        mp!!.start()
                        testua=getString(R.string.auzoa4)
                    }
                }
            }
            "7"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.sardinera1audioa)
                        mp!!.start()
                        testua=getString(R.string.sardinera1)
                    }
                    "2"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.sardinera2audioa)
                        mp!!.start()
                        testua=getString(R.string.sardinera2)
                    }
                    "3"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.sardinera3audioa)
                        mp!!.start()
                        testua=getString(R.string.sardinera3)
                    }
                    "4"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.sardinera4audioa)
                        mp!!.start()
                        testua=getString(R.string.sardinera4)
                    }

                }
            }
            "8"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.asmakizuna1audioa)
                        mp!!.start()
                        testua=getString(R.string.asmakizuna1)
                    }
                    "3"->{
                        val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.asmakizuna2audioa)
                        mp!!.start()
                    }
                   "2"->{
                       val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.asmakizuna3audioa)
                       mp!!.start()
                       testua=getString(R.string.asmakizuna2)
                    }

                }
            }
            
        }
        return testua
    }

}