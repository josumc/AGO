package com.g2.ago

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_a_p.*


class APFragment : Fragment() {
    private lateinit var bd:Base_de_Datos
    val respuesta:String="emakume"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a_p, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        btnValidar.setOnClickListener{
            if(txtEmakume.text.toString().toLowerCase().equals(respuesta)){
                val mp:MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.ondo)
                Sharedapp.puntopartida.Partida = "8"
                Sharedapp.puntojuego.Juego = "1"
                if (Sharedapp.tipousu.tipo != "profesor"){
                    bd = Base_de_Datos(requireContext(), "bd", null, 1)
                    bd.actualizar(Sharedapp.users.User.toString(), getString(R.string.finish))
                }
                bd = Base_de_Datos(requireContext(), "bd", null, 1)
                bd.actualizar(Sharedapp.users.User.toString(), getString(R.string.finish))
                replaceFragment(R.id.FragmentMapaJuego, LetraFragment())
                replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())
                Toast.makeText(requireContext(), getString(R.string.acierto), Toast.LENGTH_SHORT).show()
                mp!!.start()
            }else{
                val mp:MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.txarto)
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
                mp!!.start()
            }
        }
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
}