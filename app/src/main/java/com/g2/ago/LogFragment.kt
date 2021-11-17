package com.g2.ago

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.btnLog.setOnClickListener() {
            if (txtpsw.text.toString().equals("123456Aa")) {
                Sharedapp.tipousu.tipo = "profesor"
                val fragment: Fragment = ModoJuegoFragment()
                val m: MainActivity? = activity as MainActivity?
                if (m != null) {
                    m.replaceFragment(fragment)
                }
            }
        }
        return binding.root

    }
}