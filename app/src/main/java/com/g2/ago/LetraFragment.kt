package com.g2.ago

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_letra.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.nio.file.Files.find

class LetraFragment : Fragment() {

    lateinit var cofreanimation:AnimationDrawable
    private lateinit var vistaanimada: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_letra, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //creamos la animacion del cofre
        val img= view.findViewById<ImageView>(R.id.imgcofre)
        if (img != null) {
            animacion_cofre(img)
        }
        //Ejecutamos la animacion de la moneda
        val moneda= view.findViewById<ImageView>(R.id.imgmoneda)
        if (moneda != null) {
            vistaanimada= AnimationUtils.loadAnimation(requireContext(),
                R.anim.view_animacion)
            moneda.startAnimation(vistaanimada)
        }

    }
    //funcion para animar el cofre
        fun animacion_cofre(imagen: ImageView){
            imagen.apply {
                setBackgroundResource(R.drawable.animation)
                cofreanimation = background as AnimationDrawable
            }
            cofreanimation.start()
    }

}