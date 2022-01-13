package com.g2.ago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.FragmentInfoRutaBinding
import kotlinx.android.synthetic.main.fragment_info_ruta.*

lateinit var binding: FragmentInfoRutaBinding
class InfoRutaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoRutaBinding.inflate(inflater, container, false)

        //Genera el nombre del string que tiene que buscar
        val parada ="parada"+ Sharedapp.puntopartida.Partida

        //Usando la variable parada, busca el número de ID
        val resID = resources.getIdentifier(parada,"string", activity!!.packageName)

        //getString() recibe el ID que hemos recogido en la línea anterior y carga ese texto en el textView
        binding.Ubicacion.text=getString(resID)

        binding.EmpezarJuego.text=getString(R.string.Hasi)
        binding.EmpezarJuego.setOnClickListener{
            Toast.makeText(requireContext(), "${EmpezarJuego.isEnabled}", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    fun hasi(activar:Boolean){
//        EmpezarJuego.isEnabled = arguments!!.getBoolean("activar")
        binding.EmpezarJuego.isEnabled = activar
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}