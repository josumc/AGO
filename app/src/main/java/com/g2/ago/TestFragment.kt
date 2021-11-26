package com.g2.ago

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_test.*
import kotlinx.android.synthetic.main.fragment_vf.*

class TestFragment : Fragment() {

    private var bd:Base_de_Datos = Base_de_Datos(requireContext(), "bd", null, 1)
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
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                Sharedapp.puntopartida.Partida = "6"
                Sharedapp.puntojuego.Juego = "1"
                bd.actualizar(Sharedapp.users.User.toString(), "8")
                replaceFragment(LetraFragment())
            }else{
                var mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.txarto)
                Toast.makeText(requireContext(), "Alguna respuesta esta mal", Toast.LENGTH_SHORT).show()
                mp!!.start()
                check="ok"
            }
        }
    }
    fun replaceFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit()
    }
}