package com.g2.ago

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.ActivityJuegoBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_juego.*

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

        button.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationView.bringToFront()

        fragmentTop = MapsFragment2()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragmentTop).commit()

        fragmentBot = InfoRutaFragment()
        supportFragmentManager.beginTransaction().replace(R.id.FragmentExplicacionJuego, fragmentBot).commit()


        navigationView.setNavigationItemSelectedListener(this)

        menu.findItem(R.id.nav_alumno).isVisible = false

        if (Sharedapp.tipousu.tipo == "profesor"){
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
            if(dato != "superado"){
                    var dato_act = (dato.toInt() + 1).toString()
                    Sharedapp.puntopartida.Partida = dato_act
            }
            Sharedapp.puntojuego.Juego="1"
        }
        if (dato == "superado"){
            startActivity(Intent(this, JuegoActivity::class.java))
            finish()
        }else if (dato == "acaba"){
            finish()
            startActivity(Intent(this, MainActivity::class.java))

        }else if (dato.toInt() in 0..6){
            replaceMapFragment(FotosFragment())
            replaceExplFragment(ExplicacionFragment())
        }
    }

    override fun activarBoton(dato: Boolean) {
        InfoRutaFragment().hasi(dato)
    }

    fun replaceMapFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.FragmentMapaJuego, fragment).addToBackStack("MapsFragment").commit()
    }

    fun replaceExplFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.FragmentExplicacionJuego, fragment).addToBackStack("RutaFragment").commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            slideView(binding.FragmentMapaJuego, 0)
            super.onBackPressed()
        }


        if (Sharedapp.atras.atras == "mapa"){
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }else if (Sharedapp.atras.atras == "juegos"){
            replaceExplFragment(ExplicacionFragment())
            replaceMapFragment(MapsFragment2())
        }

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
                System.exit(0)
                finishAndRemoveTask()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    fun slideView(view: View, endH: Int) {

        val slideAnimator = ValueAnimator
            .ofInt(view.height, endH)
            .setDuration(500)

        slideAnimator.addUpdateListener {
            val value = it.animatedValue
            view.layoutParams.height = value as Int
            view.requestLayout()
        }

        val animationSet = AnimatorSet()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(slideAnimator)
        animationSet.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}




