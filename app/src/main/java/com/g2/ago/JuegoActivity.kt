package com.g2.ago

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

class JuegoActivity : AppCompatActivity(), Comunicador {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val x : Fragment = MapsFragment()
            val transacionMapaJuego = supportFragmentManager.beginTransaction().add(R.id.fragmentMapaJuego, x).addToBackStack(null).commit()
            val y : Fragment = LogFragment() //Hay que cambiar este fragment, ahora solo es para pruebas
            val transacionExplicacion = supportFragmentManager.beginTransaction().add(R.id.fragmentExplicacion, y).addToBackStack(null).commit()
        }

            return
        }

    override fun onPasarDato(dato: String) {
        TODO("Not yet implemented")
    }
}