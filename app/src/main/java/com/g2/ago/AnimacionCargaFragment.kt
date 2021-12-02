package com.g2.ago

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.RotateAnimation
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_animacion_carga.*
import kotlinx.coroutines.*

class AnimacionCargaFragment : Fragment() {
    private lateinit var vistaanimada:RotateAnimation
    lateinit var db: FirebaseFirestore
    private lateinit var bd:Base_de_Datos


    override fun onResume() {
        super.onResume()
        //animacion()
        var nombre = ""
        var Punto = ""
        bd = Base_de_Datos(requireContext(), "bd", null, 1)
        bd.profes()
        db = FirebaseFirestore.getInstance()
        db.collection("Players")
            .get()
            .addOnSuccessListener { resultado ->
                for (array in resultado) {
                    nombre = array.id
                    Punto = array.data["Partida"].toString()
                    println(nombre)
                    bd.insertar_ranking(nombre, Punto)

                    val imagen = imgCarga
                    vistaanimada = RotateAnimation(0f, 100f, 0f, 0f)
                    vistaanimada.duration = 3000
                    vistaanimada.fillAfter = true
                    imagen.startAnimation(vistaanimada)
                    var fragment = RankingProfesFragment()
                    replaceFragment(fragment)
                }
            }
    }

                override fun onCreateView(
                    inflater: LayoutInflater, container: ViewGroup?,
                    savedInstanceState: Bundle?
                ): View? {
                    // Inflate the layout for this fragment
                    return inflater.inflate(R.layout.fragment_animacion_carga, container, false)


                }
                fun replaceFragment(fragment: Fragment) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentPrincipal, fragment).commit()
                }
}