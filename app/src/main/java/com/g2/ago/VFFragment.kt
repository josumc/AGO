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
            }
            if(!rbtn2E.isChecked){
                check = "mal"
                txtVF2.setTextColor(Color.RED)
            }
            if(!rbtn3E.isChecked){
                check = "mal"
                txtVF3.setTextColor(Color.RED)
            }
            if(!rbtn4G.isChecked){
                check = "mal"
                txtVF4.setTextColor(Color.RED)
            }
            if(!rbtn5E.isChecked){
                check = "mal"
                txtVF5.setTextColor(Color.RED)
            }
            if(check.equals("ok")){
                MediaPlayer.create(requireContext(), R.raw.ondo).start()
                replaceFragment(LetraFragment())
            }else{
                val mp:MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.txarto)
                Toast.makeText(requireContext(), "Alguna respuesta esta mal", Toast.LENGTH_SHORT).show()
                mp!!.start()
                check="ok"
            }
        }
    }
    fun replaceFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit()
    }
}