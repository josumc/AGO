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
   private lateinit var bd:Base_de_Datos

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
                Toast.makeText(requireContext(), getString(R.string.acierto), Toast.LENGTH_SHORT).show()
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                Sharedapp.puntojuego.Juego = "5"
                if (Sharedapp.tipousu.tipo != "profesor"){
                    bd = Base_de_Datos(requireContext(), "bd", null, 1)
                    bd.actualizar(Sharedapp.users.User.toString(), "7")
                }
                replaceFragment(R.id.FragmentMapaJuego, LetraFragment())
                replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())
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