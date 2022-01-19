package com.g2.ago

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
   private var check:Int=0
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
            println("este es el chech $check")
            if(check == 7){
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                Sharedapp.puntojuego.Juego = "5"
                if (Sharedapp.tipousu.tipo != "profesor"){
                    bd = Base_de_Datos(requireContext(), "bd", null, 1)
                    bd.actualizar(Sharedapp.users.user.toString(), "7")
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
        check += 1
        spinner.isEnabled=false
        //spinner.setBackgroundColor(Color.GREEN)
    }
    fun comprobar (){
        check = 0
        if (spinner1.selectedItem.equals("Santurce")){
            spinner = spinner1
            acierto()
        }
        if (spinner2.selectedItem.equals("salla")){
            spinner = spinner2
            acierto()
        }
        if (spinner3.selectedItem.equals("corriendo")){
            spinner = spinner3
            acierto()
        }
        if (spinner4.selectedItem.equals("gritando")){
            spinner = spinner4
            acierto()
        }
        if (spinner5.selectedItem.equals("sardinitas")) {
            spinner = spinner5
            acierto()
        }
        if (spinner6.selectedItem.equals("Santurce")){
            spinner = spinner6
            acierto()
        }
        if (spinner7.selectedItem.equals("yo")){
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