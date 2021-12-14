package com.g2.ago

import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.ActivityJuegoBinding
import com.google.android.material.navigation.NavigationView


class JuegoActivity : DrawerActivity(), Comunicador,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding : ActivityJuegoBinding
    lateinit var fragmentTop: Fragment
    lateinit var fragmentBot: Fragment
    var bundle:Bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        binding = ActivityJuegoBinding.inflate(layoutInflater)
        binding.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN


        fragmentTop = MapsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment1Juego, fragmentTop).commit()

        fragmentBot = InfoRutaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment2Juego, fragmentBot).commit()

//Para pruebas
//        if (Sharedapp.puntopartida.Partida.equals("0")){
//            replaceExplFragment(ExplicacionFragment())
//        }else{
//            replaceExplFragment(InfoRutaFragment())
//        }
    }

    override fun onPasarDato(dato: String) {
        if(Sharedapp.modolibre.modo){
            println("Información a encontrar: ")
             var dato_act=(dato.toInt()+1).toString()
            Sharedapp.puntopartida.Partida=dato_act
            println(Sharedapp.puntopartida.Partida)
            Sharedapp.puntojuego.Juego="1"
            println(Sharedapp.puntojuego.Juego)
        }
        when(dato){
            "0" -> {
                replaceMapFragment(FotosFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "1" -> {
                replaceMapFragment(FotosFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "2" -> {
                replaceMapFragment(FotosFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "3" -> {
                replaceMapFragment(FotosFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "4" -> {
                replaceMapFragment(FotosFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "5" ->{
                replaceMapFragment(FotosFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "6" -> {
                replaceMapFragment(FotosFragment())
                replaceExplFragment(ExplicacionFragment())
            }
        }
    }

    override fun activarBoton(dato: Boolean) {
//        bundle.putBoolean("activar", dato)
//        var fragment:Fragment=InfoRutaFragment()
//        fragment.arguments=bundle
    }

    fun replaceMapFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment1Juego, fragment).addToBackStack("MapsFragment").commit()
    }

    private fun replaceExplFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment2Juego, fragment).commit()
    }
}




