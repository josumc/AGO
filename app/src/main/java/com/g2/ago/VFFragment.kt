package com.g2.ago

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_vf.*

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
                check = "mal"
                txtVF1.setTextColor(Color.RED)
            }else{
                txtVF1.setTextColor(Color.GREEN)
            }
            if(!rbtn2E.isChecked){
                check = "mal"
                txtVF2.setTextColor(Color.RED)
            }else{
                txtVF2.setTextColor(Color.GREEN)
            }
            if(!rbtn3E.isChecked){
                check = "mal"
                txtVF3.setTextColor(Color.RED)
            }else{
                txtVF3.setTextColor(Color.GREEN)
            }
            if(!rbtn4G.isChecked){
                check = "mal"
                txtVF4.setTextColor(Color.RED)
            }else{
                txtVF4.setTextColor(Color.GREEN)
            }
            if(!rbtn5E.isChecked){
                check = "mal"
                txtVF5.setTextColor(Color.RED)
            }else{
                txtVF5.setTextColor(Color.GREEN)
            }
            if(check.equals("ok")){
                Toast.makeText(requireContext(), getString(R.string.acierto), Toast.LENGTH_SHORT).show()
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                Sharedapp.puntopartida.Partida = "7"
                Sharedapp.puntojuego.Juego = "4"
                if (Sharedapp.tipousu.tipo != "profesor"){
                    bd = Base_de_Datos(requireContext(), "bd", null, 1)
                    bd.actualizar(Sharedapp.users.User.toString(), "6")
                }
                replaceFragment(LetraFragment())
            }else{
                val mp:MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.txarto)
                Toast.makeText(requireContext(), getString(R.string.preguntamal), Toast.LENGTH_SHORT).show()
                mp!!.start()
                check="ok"
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (transaction != null) {
            transaction.replace(R.id.FragmentMapaJuego, fragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }
}