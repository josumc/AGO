package com.g2.ago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class AnimacionCargaFragment : Fragment() {
    lateinit var db: FirebaseFirestore
    private lateinit var bd:Base_de_Datos


    override fun onResume() {
        super.onResume()
        var usuario: String
        var Punto: String
        bd = Base_de_Datos(requireContext(), "bd", null, 1)
        bd.profes()
        db = FirebaseFirestore.getInstance()
        db.collection("Players")
            .get()
            .addOnSuccessListener { resultado ->
                for (array in resultado) {
                    usuario = array.id
                    Punto = array.data["Partida"].toString()
                    println(usuario)
                    bd.insertar_ranking(usuario, Punto)
                    val fragment = RankingProfesFragment()
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