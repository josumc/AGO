package com.g2.ago

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTest.setOnClickListener{
            if(!rbtn1D.isChecked){
                error(txtTest1)
            }else{
                acierto(txtTest1)
            }
            if(!rbtn2B.isChecked){
                error(txtTest2)
            }else{
                acierto(txtTest2)
            }
            if(!rbtn3C.isChecked){
                error(txtTest3)
            } else{
                acierto(txtTest3)
            }
            if(!rbtn4A.isChecked){
                error(txtTest4)
            }else{
                acierto(txtTest4)
            }
            if(!rbtn5C.isChecked){
                error(txtTest5)
            }else{
                acierto(txtTest5)
            }
            if(check.equals("ok")){
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                Sharedapp.puntojuego.Juego = "4"
                if (Sharedapp.tipousu.tipo != "profesor"){
                    bd = Base_de_Datos(requireContext(), "bd", null, 1)
                    bd.actualizar(Sharedapp.users.user.toString(), "5")
                }
                //fragment por el que lo reemplazamos
                val fragment: Fragment = AnimacionFinalFragment()
                replaceFragment(R.id.FragmentMapaJuego, LetraFragment())
                replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())
            }else{
                val mp: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.txarto)
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
    fun error(Texto: TextView){
        check = "mal"
        Texto.setTextColor(Color.RED)
    }
    fun acierto(Texto: TextView){
        Texto.setTextColor(Color.GREEN)
    }
}
