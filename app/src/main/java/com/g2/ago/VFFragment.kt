package com.g2.ago

import android.app.Activity
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_vf.*
import java.lang.Error

class VFFragment : Fragment() {
    private lateinit var bd:Base_de_Datos
    var check:String = "ok"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnVF.setOnClickListener{
            if(!rbtn1G.isChecked){
                error(txtVF1)
            }else{
                acierto(txtVF1)
            }
            if(!rbtn2E.isChecked){
                error(txtVF2)
            }else{
                acierto(txtVF2)
            }
            if(!rbtn3E.isChecked){
                error(txtVF3)
            }else{
                acierto(txtVF3)
            }
            if(!rbtn4G.isChecked){
                error(txtVF4)
            }else{
                acierto(txtVF4)
            }
            if(!rbtn5E.isChecked){
                error(txtVF5)
            }else{
                acierto(txtVF5)
            }
            if(check.equals("ok")){
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                Sharedapp.puntojuego.Juego = "4"
                if (Sharedapp.tipousu.tipo != "profesor"){
                    bd = Base_de_Datos(requireContext(), "bd", null, 1)
                    bd.actualizar(Sharedapp.users.User.toString(), "6")
                }
                replaceFragment(R.id.FragmentMapaJuego, LetraFragment())
                replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())

            }else{
                val mp:MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.txarto)
                Toast.makeText(requireContext(), getString(R.string.preguntamal), Toast.LENGTH_SHORT).show()
                mp!!.start()
                check="ok"
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
    fun error(Texto:TextView){
        check = "mal"
        Texto.setTextColor(Color.RED)
    }
    fun acierto(Texto:TextView){
        Texto.setTextColor(Color.GREEN)
    }
}