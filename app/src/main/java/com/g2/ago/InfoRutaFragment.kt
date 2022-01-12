package com.g2.ago

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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