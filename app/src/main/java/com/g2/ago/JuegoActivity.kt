package com.g2.ago

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.ActivityJuegoBinding

class JuegoActivity : AppCompatActivity(), Comunicador {

    lateinit var binding : ActivityJuegoBinding
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        binding = ActivityJuegoBinding.inflate(layoutInflater)

        binding.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN


        fragment = MapsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentMapaJuego, fragment).commit()
    }

    override fun onPasarDato(dato: String) {
        when(dato){
            "m0" -> replaceFragment(PuzzleFragment())
            "m1" -> replaceFragment(MemoryFragment())
            "m2" -> replaceFragment(ParrafoFragment())
            "m3" -> replaceFragment(PreguntasFragment())
            "m4" -> replaceFragment(TestFragment())
            "m5" -> replaceFragment(VFFragment())
            "m6" -> replaceFragment(SLFragemt())
        }
    }

    override fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMapaJuego, fragment).addToBackStack(MapsFragment().toString()).commit()
    }


}