package com.g2.ago

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.g2.ago.databinding.FragmentLogBinding
import kotlinx.android.synthetic.main.fragment_log.*

class LogFragment : Fragment() {

    lateinit var binding: FragmentLogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogBinding.inflate(layoutInflater)
//Hacemos que al pulsar el boton la contrseña sea 123456Aa y si es correcta le cambia a tipo profesor y recarga la main con este modo
        binding.btnLog.setOnClickListener() {
            if (txtpsw.text.toString().equals("123456Aa")) {
                Sharedapp.tipousu.tipo = "profesor"
                val fragment: Fragment = ModoJuegoFragment()
                val m: MainActivity? = activity as MainActivity?
                if (m != null) {
                    m.menu.findItem(R.id.nav_profe).isVisible = false
                    m.menu.findItem(R.id.nav_cerrar_sesion).isVisible = true
                    m.replaceFragment(fragment)
                }
            }else{
                Toast.makeText(requireContext(), "La contraseña introducida no es correcta", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root

    }
}