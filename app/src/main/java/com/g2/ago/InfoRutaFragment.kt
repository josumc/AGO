package com.g2.ago

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.g2.ago.databinding.FragmentInfoRutaBinding
import kotlinx.android.synthetic.main.fragment_info_ruta.*

class InfoRutaFragment : Fragment() {

    var _binding: FragmentInfoRutaBinding?=null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentInfoRutaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments!=null&& arguments!!.getBoolean("activar")){
            EmpezarJuego.isEnabled=true
        }else{
            EmpezarJuego.isEnabled=false
        }
        EmpezarJuego.setOnClickListener{
            Toast.makeText(requireContext(), "${EmpezarJuego.isEnabled}", Toast.LENGTH_SHORT).show()
        }
    }
}