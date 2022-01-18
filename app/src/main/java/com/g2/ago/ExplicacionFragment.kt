package com.g2.ago

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.g2.ago.databinding.FragmentExplicacionBinding
import kotlinx.android.synthetic.main.activity_juego.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ExplicacionFragment : Fragment() {
    lateinit var binding: FragmentExplicacionBinding
    var Activityppal:Comunicador?=null
    var testua:String=""
    lateinit var mp: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentExplicacionBinding.inflate(layoutInflater)
        cargarTexto()
        binding.pasarFase.setOnClickListener{
                val sharedappAct=(Sharedapp.puntojuego.Juego.toInt()+1).toString()
                //mp.stop()
                Sharedapp.puntojuego.Juego=sharedappAct
                cargarTexto()
               replaceFragment(R.id.FragmentMapaJuego, FotosFragment())
        }
        return binding.root
    }
    fun cargarTexto(){
        val explicacion=explicaciones()
        binding.textView.typeWrite(this, explicacion, 33L)
    }
    //Esta funcion se utiliza para cargar las letas del texto a utilizar de una en una
    fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
        this@typeWrite.text = ""
        lifecycleOwner.lifecycleScope.launch {
            repeat(text.length) {
                delay(intervalMs)
                this@typeWrite.text = text.take(it + 1)
            }
            binding.pasarFase.isEnabled = true
        }
    }
    fun explicaciones(): String {
        binding.pasarFase.isEnabled = false
        when(Sharedapp.puntopartida.Partida){
            "0"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.hasiera1audioa)
                        mp.start()
                        testua=getString(R.string.hasiera1)
                    }
                    "2"-> {
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.hasiera2audioa)
                        mp.start()
                        testua=getString(R.string.hasiera2)
                    }
                    "3" ->{
                        mp.stop()
                        superado()
                        Sharedapp.puntopartida.Partida = "1"
                    }
                }
            }
            "1"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.portua1audioa)
                        mp.start()
                        testua=getString(R.string.portua1)
                    }
                    "2"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.portua2audioa)
                        mp.start()
                        testua=getString(R.string.portua2)

                    }
                    "4"->{
                        binding.pasarFase.isVisible = true
                        //mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.portua3audioa)
                        mp.start()
                        testua=getString(R.string.portua3)
                    }
                    "5"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.portua4audioa)
                        mp.start()
                        testua=getString(R.string.portua4)
                    }
                    "6"->{
                        mp.stop()
                        superado()
                        Sharedapp.puntopartida.Partida = "2"
                    }
                }
            }
            "2"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.kofradia1audioa)
                        mp.start()
                        testua=getString(R.string.kofradia1)
                    }
                    "2"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.kofradia2audioa)
                        mp.start()
                        testua=getString(R.string.kofradia2)
                        //binding.pasarFase.isVisible = false
                    }
                    "4"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.kofradia3audioa)
                        mp.start()
                        testua=getString(R.string.kofradia3)
                    }
                    "5"->{
                        mp.stop()
                        superado()
                        Sharedapp.puntopartida.Partida = "3"
                    }
                }
            }
            "3"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.sotera1audioa)
                        mp.start()
                        testua=getString(R.string.sotera1)
                    }
                    "2"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.soteraabestia)
                        testua=""
                        mp.start()
                    }
                    "3"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.sotera2audioa)
                        mp.start()
                        testua=getString(R.string.sotera2)
                    }
                    "4"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.sotera3audioa)
                        mp.start()
                        testua=getString(R.string.sotera3)
                        binding.pasarFase.isVisible = false
                    }
                    "5"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.sotera4audioa)
                        mp.start()
                        testua=getString(R.string.sotera4)
                    }
                    "6"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.sotera5audioa)
                        mp.start()
                        testua=getString(R.string.sotera5)
                    }
                    "7"->{
                        mp.stop()
                        superado()
                        Sharedapp.puntopartida.Partida = "4"
                    }
                }
            }
            "4"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.kioskoa1audioa)
                        mp.start()
                        testua=getString(R.string.kioskoa1)
                    }
                    "2"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.kioskoa2audioa)
                        mp.start()
                        testua=getString(R.string.kioskoa2)
                        binding.pasarFase.isVisible = false
                    }
                    "3"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.kioskoa3audioa)
                        mp.start()
                        testua=getString(R.string.kioskoa3)
                    }
                    "4"->{
                        mp.stop()
                        superado()
                        Sharedapp.puntopartida.Partida = "5"
                    }
                }
            }
            "5"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.udaletxea1audioa)
                        mp.start()
                        testua=getString(R.string.udaletxea1)
                    }
                    "2"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.udaletxea2audioa)
                        mp.start()
                        testua=getString(R.string.udaletxea2)
                    }
                    "3"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.udaletxea3audioa)
                        mp.start()
                        testua=getString(R.string.udaletxea3)
                        binding.pasarFase.isVisible = false
                    }
                    "4"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.udaletxea4audioa)
                        mp.start()
                        testua=getString(R.string.udaletxea4)
                    }
                    "5"->{
                        mp.stop()
                        superado()
                        Sharedapp.puntopartida.Partida = "6"
                    }
                }
            }
            "6"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.auzoa1audioa)
                        mp.start()
                        testua=getString(R.string.auzoa1)
                    }
                    "2"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.auzoa2audioa)
                        mp.start()
                        testua=getString(R.string.auzoa2)
                    }
                    "3"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.auzoa3audioa)
                        mp.start()
                        testua=getString(R.string.auzoa3)
                        binding.pasarFase.isVisible = false
                    }
                    "4"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.auzoa4audioa)
                        mp.start()
                        testua=getString(R.string.auzoa4)
                    }
                    "5"->{
                        mp.stop()
                        superado()
                        Sharedapp.puntopartida.Partida = "7"
                    }
                }
            }
            "7"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.sardinera1audioa)
                        mp.start()
                        testua=getString(R.string.sardinera1)
                    }
                    "2"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.sardinera2audioa)
                        mp.start()
                        testua=getString(R.string.sardinera2)
                    }
                    "3"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.desdesanturceabilbao)
                        mp.start()
                        testua=""
                    }
                    "4"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.sardinera3audioa)
                        mp.start()
                        testua=getString(R.string.sardinera3)
                        binding.pasarFase.isVisible = false
                    }
                    "5"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.sardinera4audioa)
                        mp.start()
                        testua=getString(R.string.sardinera4)
                    }
                    "6"->{
                        mp.stop()
                        if(Sharedapp.modolibre.modo){
                            superado()
                        }else{
                            Sharedapp.puntopartida.Partida = "8"
                            Sharedapp.puntojuego.Juego = "1"
                            replaceFragment(R.id.FragmentMapaJuego, FotosFragment())
                            replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())
                        }
                    }
                }
            }
            "8"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        mp = MediaPlayer.create(requireContext(), R.raw.asmakizuna1audioa)
                        mp.start()
                        testua=getString(R.string.asmakizuna1)
                    }
                   "2"->{

                       mp.stop()
                       mp = MediaPlayer.create(requireContext(), R.raw.asmakizuna3audioa)
                       mp.start()
                       testua=getString(R.string.asmakizuna2)
                    }
                    "3"->{
                        mp.stop()
                        mp = MediaPlayer.create(requireContext(), R.raw.asmakizuna3audioa)
                        mp.start()
                        testua=getString(R.string.asmakizuna2)

                    }
                    "4"->{
                        runBlocking {
                            launch {
                                delay(50000L)
                                mp.stop()
                                Activityppal=requireContext() as Comunicador
                                Activityppal!!.onPasarDato("acaba")
                            }
                            mp = MediaPlayer.create(requireContext(), R.raw.desdesanturceabilbao)
                            testua=""
                            mp.start()
                        }
                    }
                }
            }
            
        }
        return testua
    }
    fun replaceFragment(Contenedor:Int, fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if(transaction != null) {
            transaction.replace(Contenedor, fragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }
    fun superado(){
        Activityppal=requireContext() as Comunicador
        Activityppal!!.onPasarDato("superado")
        }


    override fun onDestroyView() {
        super.onDestroyView()
        mp.stop()
    }
}