package com.g2.ago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Comunicador {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var button: FloatingActionButton
    lateinit var fragment: Fragment



    override fun onCreate(savedInstanceState: Bundle?) {
        SystemClock.sleep(1000)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerlayout)
        navigationView = findViewById(R.id.nav_view)
        button = findViewById(R.id.menuButton)

        button.setOnClickListener(){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        navigationView.bringToFront()

        if (Sharedapp.tipousu.tipo.equals("profesor")){
            fragment = ModoJuegoFragment()
            replaceFragment(fragment)
        }else if (Sharedapp.tipousu.tipo.equals("alumno")) {
            fragment = RankingFragment()
            replaceFragment(fragment)
        }

        navigationView.setNavigationItemSelectedListener(this)
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

            }
            R.id.nav_mapa -> {

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

    override fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit()
    }

}