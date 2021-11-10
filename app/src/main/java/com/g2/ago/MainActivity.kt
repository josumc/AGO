package com.g2.ago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        super.onCreateOptionsMenu(menu)
//        val inflater = menuInflater
//
//        if (Sharedapp.tipousu.tipo.equals("profesor")){
//            inflater.inflate(R.menu.menu, menu)
//        }else if (Sharedapp.tipousu.tipo.equals("alumno")){
//            inflater.inflate(R.menu.menualumno, menu)
//        }
//        return true
//    }

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_ranking -> {
               if (Sharedapp.tipousu.tipo.equals("profesor")){
                    //Activar fragment ranking
                }else if (Sharedapp.tipousu.tipo.equals("alumno")){
                    //Activar fragment de profesor
                    Sharedapp.tipousu.tipo = "profesor"
                   finish()
                   startActivity(Intent(this, MainActivity::class.java))


                }
            }
            R.id.nav_quienes -> {
                startActivity(Intent(this, QSActivity::class.java))
            }
            R.id.nav_cerrar_sesion -> {
                //Activar fragment

                //Se cierra la sesion
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
            }
            R.id.nav_salir -> {
                finishAndRemoveTask()
            }
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        SystemClock.sleep(1000)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerlayout)
        navigationView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        navigationView.bringToFront()
        var toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_abrir, R.string.menu_cerrar)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (Sharedapp.tipousu.tipo.equals("profesor")){
            val x : Fragment = LogFragment()
            val transacion = supportFragmentManager.beginTransaction().add(R.id.fragmentPrincipal, x).addToBackStack(null).commit()
        }else if (Sharedapp.tipousu.tipo.equals("alumno")){
            val y : Fragment = RankingFragment()
            val transacion = supportFragmentManager.beginTransaction().add(R.id.fragmentPrincipal, y).addToBackStack(null).commit()
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
            R.id.nav_ranking -> {
                if (Sharedapp.tipousu.tipo.equals("profesor")){
                    //Activar fragment ranking
                }else if (Sharedapp.tipousu.tipo.equals("alumno")){
                    //Activar fragment de profesor
                    Sharedapp.tipousu.tipo = "profesor"
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))


                }
            }
            R.id.nav_quienes -> {
                startActivity(Intent(this, QSActivity::class.java))
            }
            R.id.nav_cerrar_sesion -> {
                //Activar fragment

                //Se cierra la sesion
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
            }
            R.id.nav_salir -> {
                finishAndRemoveTask()
            }
        }
        return true
    }

}