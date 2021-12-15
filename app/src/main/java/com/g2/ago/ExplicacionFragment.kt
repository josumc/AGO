package com.g2.ago

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.g2.ago.databinding.FragmentExplicacionBinding
import kotlinx.coroutines.cancel
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
                mp.stop()
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
//                binding.textView.setOnClickListener {
//                    lifecycleOwner.lifecycleScope.cancel()
//                    binding.textView.text=explicaciones()
//                }
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
                        binding.pasarFase.isVisible = false
                    }
                    "4"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.portua3audioa)
                        mp.start()
                        testua=getString(R.string.portua3)
                    }
                    "5"->{
                        mp.stop()
                        Sharedapp.puntopartida.Partida = "2"
                        replaceFragment(R.id.FragmentMapaJuego, MapsFragment2())
                        replaceFragment(R.id.FragmentExplicacionJuego, InfoRutaFragment())
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
                        binding.pasarFase.isVisible = false
                    }
                    "4"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.kofradia3audioa)
                        mp.start()
                        testua=getString(R.string.kofradia3)
                    }
                    "5"->{
                        mp.stop()
                        Sharedapp.puntopartida.Partida = "3"
                        replaceFragment(R.id.FragmentMapaJuego, MapsFragment2())
                        replaceFragment(R.id.FragmentExplicacionJuego, InfoRutaFragment())
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
                    "7"->{
                        mp.stop()
                        Sharedapp.puntopartida.Partida = "4"
                        replaceFragment(R.id.FragmentMapaJuego, MapsFragment2())
                        replaceFragment(R.id.FragmentExplicacionJuego, InfoRutaFragment())
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
                    "4"->{
                        binding.pasarFase.isVisible = true
                        mp = MediaPlayer.create(requireContext(), R.raw.kioskoa3audioa)
                        mp.start()
                        testua=getString(R.string.kioskoa3)
                    }
                    "5"->{
                        mp.stop()
                        Sharedapp.puntopartida.Partida = "5"
                        replaceFragment(R.id.FragmentMapaJuego, MapsFragment2())
                        replaceFragment(R.id.FragmentExplicacionJuego, InfoRutaFragment())
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
                        Sharedapp.puntopartida.Partida = "6"
                        replaceFragment(R.id.FragmentMapaJuego, MapsFragment2())
                        replaceFragment(R.id.FragmentExplicacionJuego, InfoRutaFragment())
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
                        Sharedapp.puntopartida.Partida = "7"
                        replaceFragment(R.id.FragmentMapaJuego, MapsFragment2())
                        replaceFragment(R.id.FragmentExplicacionJuego, InfoRutaFragment())
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
                        Sharedapp.puntopartida.Partida = "8"
                        Sharedapp.puntojuego.Juego = "0"
                        replaceFragment(R.id.FragmentMapaJuego, FotosFragment())
                        replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())
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
                                replaceFragment(R.id.FragmentMapaJuego, FotosFragment())
                                replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())
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
        // val activity = JuegoActivity()
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if(transaction != null) {
            transaction.replace(Contenedor, fragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }
    fun replaceFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentPrincipal, fragment)?.commit()
    }
}