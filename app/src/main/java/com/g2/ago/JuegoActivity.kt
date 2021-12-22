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


        fragmentTop = MapsFragment2()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragmentTop).commit()

        fragmentBot = InfoRutaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment2Juego, fragmentBot).commit()

//Para pruebas
//        if (Sharedapp.puntopartida.Partida.equals("0")){
//            replaceExplFragment(ExplicacionFragment())
//        }else{
//            replaceExplFragment(InfoRutaFragment())
//        }
        navigationView.setNavigationItemSelectedListener(this)

        menu.findItem(R.id.nav_alumno).isVisible = false

        if (Sharedapp.tipousu.tipo.equals("profesor")){
            menu.findItem(R.id.nav_profe).isVisible = false
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = true

        }else{
            menu.findItem(R.id.nav_profe).isVisible = true
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = false
        }
        menu.findItem(R.id.nav_ranking).isVisible = false
    }

    override fun onPasarDato(dato: String) {
        if(Sharedapp.modolibre.modo){
            val datoact=(dato.toInt()+1).toString()
            Sharedapp.puntopartida.Partida=datoact
            Sharedapp.puntojuego.Juego="1"
        }
        replaceMapFragment(FotosFragment())
        replaceExplFragment(ExplicacionFragment())
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
        supportFragmentManager.beginTransaction().replace(R.id.FragmentExplicacionJuego, fragment).addToBackStack("RutaFragment").commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
        replaceMapFragment(MapsFragment2())
        replaceExplFragment(InfoRutaFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val intent = Intent(this, MainActivity::class.java)

        when(item.itemId){
            R.id.nav_inicio -> {
                startActivity(intent)
                finish()
            }
            R.id.nav_quienes -> {
                intent.putExtra("fragment", "QSFragment()")
                startActivity(intent)
                finish()
            }
            R.id.nav_alumno -> {
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
            }
            R.id.nav_profe -> {
                intent.putExtra("fragment", "LogFragment()")
                startActivity(intent)
                finish()
            }
            R.id.nav_cerrar_sesion -> {
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
                startActivity(intent)
                finish()
            }
            R.id.nav_salir -> {
                finishAndRemoveTask()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}




