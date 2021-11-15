package com.g2.ago

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.ActivityJuegoBinding

class JuegoActivity : AppCompatActivity(), Comunicador {

    lateinit var binding : ActivityJuegoBinding
    lateinit var fragmentTop: Fragment
    lateinit var fragmentBot: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        binding = ActivityJuegoBinding.inflate(layoutInflater)

        binding.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN


        fragmentTop = MapsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragmentTop).commit()

        fragmentBot = InfoRutaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentExplicacionJuego, fragmentBot).commit()
    }

    override fun onPasarDato(dato: String) {
        when(dato){
            "m0" -> replaceMapFragment(PuzzleFragment())
            "m1" -> replaceMapFragment(MemoryFragment())
            "m2" -> replaceMapFragment(ParrafoFragment())
            "m3" -> replaceMapFragment(PreguntasFragment())
            "m4" -> replaceMapFragment(TestFragment())
            "m5" -> replaceMapFragment(VFFragment())
            "m6" -> replaceMapFragment(SLFragemt())
        }
        replaceExplFragment(InfoRutaFragment())
    }

    override fun replaceFragment(fragment: Fragment) {

    }

    private fun replaceMapFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentMapaJuego, fragment).commit()
    }

    private fun replaceExplFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FragmentExplicacionJuego, fragment).commit()
    }


}