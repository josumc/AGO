package com.g2.ago

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Comunicador {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var menu: Menu
    lateinit var button: FloatingActionButton
    lateinit var fragment: Fragment
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        drawerLayout = findViewById(R.id.drawerlayout)
        navigationView = findViewById(R.id.nav_view)
        button = findViewById(R.id.MenuButton)
        menu = navigationView.menu

        button.setOnClickListener(){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        navigationView.bringToFront()

        var fr = intent.extras

        if (fr != null) {
            when(fr.get("fragment")){
                "LogFragment()" -> replaceFragment(LogFragment())
                "QSFragment()" -> replaceFragment(QSFragment())
                "RankingFragment()" -> replaceFragment(RankingFragment())
            }
        }else if (Sharedapp.tipousu.tipo.equals("profesor")){
            fragment = ModoJuegoFragment()
            replaceFragment(fragment)
            menu.findItem(R.id.nav_profe).isVisible = false
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = true
        }else{
            fragment = RankingFragment()
            replaceFragment(fragment)
            menu.findItem(R.id.nav_profe).isVisible = true
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = false
        }

        navigationView.setNavigationItemSelectedListener(this)

        menu.findItem(R.id.nav_alumno).isVisible = false
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
                if (Sharedapp.tipousu.tipo.equals("profesor")){
                    fragment = ModoJuegoFragment()
                    replaceFragment(fragment)
                }else if (Sharedapp.tipousu.tipo.equals("alumno")){
                    fragment = RankingFragment()
                    replaceFragment(fragment)
                }
            }
            R.id.nav_mapa -> {
                startActivity(Intent(this, JuegoActivity::class.java))
                finish()
            }
            R.id.nav_ranking -> {
                if (Sharedapp.tipousu.tipo.equals("profesor")){
                    fragment = RankingFragment()
                    replaceFragment(fragment)
                }else if (Sharedapp.tipousu.tipo.equals("alumno")){
                    fragment = LogFragment()
                    replaceFragment(fragment)
                }
            }
            R.id.nav_quienes -> {
                fragment = QSFragment()
                replaceFragment(fragment)
            }
            R.id.nav_alumno -> {
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
                fragment = LogFragment()
                replaceFragment(fragment)
            }
            R.id.nav_profe -> {
                fragment = LogFragment()
                replaceFragment(fragment)
            }
            R.id.nav_cerrar_sesion -> {
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
                fragment = RankingFragment()
                replaceFragment(fragment)
            }
            R.id.nav_salir -> {
                finishAndRemoveTask()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPasarDato(dato: String) {

    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit()
    }

}