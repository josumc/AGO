package com.g2.ago

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.ActivityJuegoBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView


class JuegoActivity : AppCompatActivity(), Comunicador,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var button: FloatingActionButton
    lateinit var menu: Menu
    lateinit var binding : ActivityJuegoBinding
    lateinit var fragmentTop: Fragment
    lateinit var fragmentBot: Fragment
    var bundle:Bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        binding = ActivityJuegoBinding.inflate(layoutInflater)
        binding.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        drawerLayout = findViewById(R.id.drawerlayout)
        navigationView = findViewById(R.id.nav_view)
        button = findViewById(R.id.MenuButton)
        menu = navigationView.menu

        button.setOnClickListener(){
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.bringToFront()

        fragmentTop = MapsFragment2()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragmentTop).commit()

        fragmentBot = InfoRutaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentExplicacionJuego, fragmentBot).commit()

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
            menu.findItem(R.id.nav_ranking).isVisible = true

        }else{
            menu.findItem(R.id.nav_profe).isVisible = true
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = false
        }
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
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragment).addToBackStack("MapsFragment").commit()
    }

    private fun replaceExplFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.FragmentExplicacionJuego, fragment).commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val intent = Intent(this, MainActivity::class.java)
        if (Sharedapp.tipousu.tipo.equals("profesor")){
            menu.findItem(R.id.nav_ranking).isVisible = true
        }else if (Sharedapp.tipousu.tipo.equals("alumno") || Sharedapp.tipousu.tipo.equals("")){
            menu.findItem(R.id.nav_ranking).isVisible = false
        }

        when(item.itemId){
            R.id.nav_inicio -> {
                startActivity(intent)
                finish()
            }
            R.id.nav_ranking -> {
                if (Sharedapp.tipousu.tipo.equals("profesor")){
                    intent.putExtra("fragment", "AnimacionCargaFragment()")
                    startActivity(intent)
                    finish()

                }else if (Sharedapp.tipousu.tipo.equals("alumno")){
                    intent.putExtra("fragment", "LogFragment()")
                    startActivity(intent)
                    finish()
                }
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




