package com.g2.ago

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_parrafo.*

class ParrafoFragment : Fragment(), AdapterView.OnItemSelectedListener{
   private lateinit var bd:Base_de_Datos
   private var check:Int=7
    private lateinit var spinner:Spinner

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
            comprobar()
            if(check == 7){
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
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if(transaction != null) {
            transaction.replace(Contenedor, fragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }
    fun acierto(){
        check = check+1
        spinner.isEnabled=false
        spinner.setBackgroundColor(Color.GREEN)
    }
    fun comprobar (){
        if (spinner1.selectedItem==1){
            spinner = spinner1
            acierto()
        }
        if (spinner2.selectedItem==3){
            spinner = spinner2
            acierto()
        }
        if (spinner3.selectedItem==2){
            spinner = spinner3
            acierto()
        }
        if (spinner4.selectedItem==3){
            spinner = spinner4
            acierto()
        }
        if (spinner5.selectedItem==2){
            spinner = spinner5
            acierto()
        }
        if (spinner6.selectedItem==1){
            spinner = spinner6
            acierto()
        }
        if (spinner7.selectedItem==3){
            spinner = spinner7
            acierto()
        }
    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}