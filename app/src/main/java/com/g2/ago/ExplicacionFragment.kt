package com.g2.ago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.g2.ago.databinding.FragmentExplicacionBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ExplicacionFragment : Fragment() {
    lateinit var binding: FragmentExplicacionBinding
    val explicacion:String="Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
            " Vestibulum in volutpat mauris. Pellentesque nec metus vel velit imperdiet rhoncus id non dui." +
            " Praesent nisi leo, dignissim eget ante a, pharetra pellentesque leo. " +
            "Phasellus neque nisi, eleifend ac ligula et, mollis gravida nunc. "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentExplicacionBinding.inflate(layoutInflater)
        binding.textView.typeWrite(this, explicacion, 33L)
        return binding.root
    }
    //Esta funcion se utiliza para cargar las letas del texto a utilizar de una en una
    fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
        this@typeWrite.text = ""
        lifecycleOwner.lifecycleScope.launch {
            repeat(text.length) {
                delay(intervalMs)
                this@typeWrite.text = text.take(it + 1)
            }
        }
    }

}