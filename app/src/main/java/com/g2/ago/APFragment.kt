package com.g2.ago

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a_p.*
import java.util.*


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
            if (txtEmakume.text.toString().lowercase(Locale.getDefault()) == respuesta) {
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                Sharedapp.puntojuego.Juego = "3"
                if (Sharedapp.tipousu.tipo != "profesor") {
                    bd = Base_de_Datos(requireContext(), "bd", null, 1)
                    bd.actualizar(Sharedapp.users.user, "8")
                }
                replaceFragment(R.id.FragmentMapaJuego, AnimacionFinalFragment())
                replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())
            }else{
                txtEmakume.setText("")
                val mp:MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.txarto)
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
                mp!!.start()

            }
        }
    }
    fun replaceFragment(Contenedor:Int, fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if(transaction != null) {
            transaction.replace(Contenedor, fragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }
}