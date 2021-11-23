package com.g2.ago

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.widget.Toast
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
    lateinit var fragment: Fragment
    lateinit var fragmentBot: Fragment

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

        fragmentTop = MapsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragmentTop).commit()

        fragmentBot = InfoRutaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentExplicacionJuego, fragmentBot).commit()

        navigationView.setNavigationItemSelectedListener(this)

        menu.findItem(R.id.nav_alumno).isVisible = false

        if (Sharedapp.tipousu.tipo.equals("profesor")){
            menu.findItem(R.id.nav_profe).isVisible = false
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = true

        }else{
            menu.findItem(R.id.nav_profe).isVisible = true
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = false
        }
    }

    override fun onPasarDato(dato: String) {
        when(dato){
            "m0" -> {
                replaceExplFragment(ExplicacionFragment())
                replaceMapFragment(PuzzleFragment())
            }
            "m1" -> {
                replaceMapFragment(MemoryFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "m2" -> {
                replaceMapFragment(ParrafoFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "m3" -> {
                replaceMapFragment(PreguntasFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "m4" -> {
                replaceMapFragment(TestFragment())
                replaceExplFragment(ExplicacionFragment())
            }
            "m5" ->{
                replaceExplFragment(ExplicacionFragment())
                replaceMapFragment(VFFragment())
            }
            "m6" -> {
            replaceExplFragment(ExplicacionFragment())

            }
        }
    }

    private fun replaceMapFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragment).commit()
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

        var intent = Intent(this, MainActivity::class.java)

        when(item.itemId){
            R.id.nav_inicio -> {
                startActivity(intent)
                finish()
            }
            R.id.nav_mapa -> {
                Toast.makeText(this, "Mapa", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_ranking -> {
                if (Sharedapp.tipousu.tipo.equals("profesor")){
                    intent.putExtra("fragment", "RankingFragment()")
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




