package com.g2.ago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
    private lateinit var db:Base_de_Datos

    override fun onCreate(savedInstanceState: Bundle?) {
        //sleep(1000)
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
        if (Sharedapp.tipousu.tipo.equals("profesor")){
            menu.findItem(R.id.nav_ranking).isVisible = true
        }else if (Sharedapp.tipousu.tipo.equals("alumno") || Sharedapp.tipousu.tipo.equals("")){
            menu.findItem(R.id.nav_ranking).isVisible = false
        }

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
            R.id.nav_ranking -> {
                if (Sharedapp.tipousu.tipo.equals("profesor")){
                    var fragment = AnimacionCargaFragment()
                    replaceFragment(fragment)

                }else{
                    fragment = LogFragment()
                    Toast.makeText(this, "Necesitas logearte como profesor para acceder a esta funcion" +
                            "", Toast.LENGTH_SHORT).show()
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
                db = Base_de_Datos(this, "bd", null, 1)
                db.profes()
                menu.findItem(R.id.nav_profe).isVisible = true
                menu.findItem(R.id.nav_cerrar_sesion).isVisible = false
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            R.id.nav_salir -> {
                val fragment: Fragment = LetraFragment()
                replaceFragment(fragment)
                //finishAndRemoveTask()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPasarDato(dato: String) {

    }

    override fun activarBoton(dato: Boolean) {

    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit()
    }

}