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
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

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
            fragment = LogFragment()
            cambiarFragment(fragment)
        }else if (Sharedapp.tipousu.tipo.equals("alumno")){
            fragment = RankingFragment()
            cambiarFragment(fragment)
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
                    fragment = LogFragment()
                    cambiarFragment(fragment)
                }
            }
            R.id.nav_quienes -> {
                fragment = QSFragment()
                cambiarFragment(fragment)
            }
            R.id.nav_profe -> {
                fragment = LogFragment()
                cambiarFragment(fragment)
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
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun cambiarFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit()
    }

}