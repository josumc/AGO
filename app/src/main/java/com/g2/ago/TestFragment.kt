package com.g2.ago

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : Fragment() {

    private lateinit var bd:Base_de_Datos
    var check:String = "ok"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTest.setOnClickListener{
            if(!rbtn1D.isChecked){
                check = "mal"
                txtTest1.setTextColor(Color.RED)
            }
            if(!rbtn2B.isChecked){
                check = "mal"
                txtTest1.setTextColor(Color.RED)
            }
            if(!rbtn3C.isChecked){
                check = "mal"
                txtTest3.setTextColor(Color.RED)
            }
            if(!rbtn4A.isChecked){
                check = "mal"
                txtTest4.setTextColor(Color.RED)
            }
            if(!rbtn5C.isChecked){
                check = "mal"
                txtTest5.setTextColor(Color.RED)
            }
            if(check.equals("ok")){
                Toast.makeText(requireContext(), getString(R.string.acierto), Toast.LENGTH_SHORT).show()
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                Sharedapp.puntopartida.Partida = "6"
                Sharedapp.puntojuego.Juego = "1"
                bd = Base_de_Datos(requireContext(), "bd", null, 1)
                bd.actualizar(Sharedapp.users.User.toString(), "5")
                replaceFragment(LetraFragment())
            }else{
                val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.txarto)
                Toast.makeText(requireContext(), getString(R.string.preguntamal), Toast.LENGTH_SHORT).show()
                mp!!.start()
                check="ok"
            }
        }
    }
    fun replaceFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit()
    }
}