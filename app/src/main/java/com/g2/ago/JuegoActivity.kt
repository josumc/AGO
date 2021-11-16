package com.g2.ago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
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
    lateinit var binding : ActivityJuegoBinding
    lateinit var fragmentTop: Fragment
    lateinit var fragmentBot: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        binding = ActivityJuegoBinding.inflate(layoutInflater)

        binding.root.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        drawerLayout = findViewById(R.id.drawerlayout)
        navigationView = findViewById(R.id.nav_view)
        button = findViewById(R.id.MenuButton)

        button.setOnClickListener(){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        navigationView.bringToFront()

        fragmentTop = MapsFragment()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragmentTop).commit()

        fragmentBot = InfoRutaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentExplicacionJuego, fragmentBot).commit()

        navigationView.setNavigationItemSelectedListener(this)
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

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_inicio -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_mapa -> {
                startActivity(Intent(this, JuegoActivity::class.java))
            }
            R.id.nav_ranking -> {
                if (Sharedapp.tipousu.tipo.equals("profesor")){
                    startActivity(Intent(this, MainActivity::class.java))

                }else if (Sharedapp.tipousu.tipo.equals("alumno")){
                    startActivity(Intent(this, MainActivity::class.java))

                }
            }
            R.id.nav_quienes -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_alumno -> {
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_profe -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_cerrar_sesion -> {
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_salir -> {
                finishAndRemoveTask()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}


