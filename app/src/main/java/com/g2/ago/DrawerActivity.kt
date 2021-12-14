package com.g2.ago

import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

open class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menu: Menu
    private lateinit var button: FloatingActionButton
    lateinit var fragment: Fragment
    private lateinit var activityName: String

    //TODAS LAS ACTIVIDADES QUE EXTIENDAN DE ESTA TENDRÁN EL MENÚ
    override fun setContentView(view: View?) {

        drawerLayout = layoutInflater.inflate(R.layout.activity_drawer, null) as DrawerLayout
        val container: FrameLayout = drawerLayout.findViewById(R.id.drawerFragmentPrincipal)
        container.addView(view)
        super.setContentView(drawerLayout)

        navigationView = drawerLayout.findViewById(R.id.nav_view)
        button = drawerLayout.findViewById(R.id.MenuButton)
        menu = navigationView.menu

        button.setOnClickListener(){
            drawerLayout.openDrawer(GravityCompat.START);
        }//onClick

        navigationView.bringToFront()

        if (Sharedapp.tipousu.tipo.equals("profesor")){
            menu.findItem(R.id.nav_profe).isVisible = false
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = true
            menu.findItem(R.id.nav_ranking).isVisible = true

        }else{
            menu.findItem(R.id.nav_profe).isVisible = true
            menu.findItem(R.id.nav_cerrar_sesion).isVisible = false
        }

        navigationView.setNavigationItemSelectedListener(this)

        val am = this.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val taskInfo = am.getRunningTasks(1)
        activityName = taskInfo[0].topActivity!!.className

    }//setContentView(view: View?)

    //AL PULSAR UNA OPCIÓN DEL MENÚ
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(activityName){

            "com.g2.santurtziapp.activitidades.MainActivity" -> {
                when(item.itemId){
                    R.id.nav_inicio -> {
                        if (Sharedapp.tipousu.tipo.equals("profesor")){

                            fragment = ModoJuegoFragment()
                            replaceFragment(fragment, 1)

                        }//is profesor
                        else{

                            fragment = RankingFragment()
                            replaceFragment(fragment, 1)

                        }//is alumno
                    }//inicio
                    R.id.nav_ranking -> {
                        if (Sharedapp.tipousu.tipo.equals("profesor")){

                            //fragment = AnimacionCargaFragment()
                            replaceFragment(fragment, 1)

                        }//is profesor
                        else{
                            fragment = LogFragment()
                            Toast.makeText(this, "Necesitas logearte como profesor para acceder a esta funcion" +
                                    "", Toast.LENGTH_SHORT).show()
                            replaceFragment(fragment, 1)
                        }//is alumno
                    }//ranking
                    R.id.nav_quienes -> {
                        fragment = QSFragment()
                        replaceFragment(fragment, 1)
                    }//quienes
                    R.id.nav_alumno -> {
                        Sharedapp.users.user = ""
                        Sharedapp.tipousu.tipo = "alumno"
                        fragment = LogFragment()
                        replaceFragment(fragment, 1)
                    }//alumno
                    R.id.nav_profe -> {
                        fragment = LogFragment()
                        replaceFragment(fragment, 1)
                    }//profe
                    R.id.nav_cerrar_sesion -> {
                        Sharedapp.users.user = ""
                        Sharedapp.tipousu.tipo = "alumno"
//                        db = Base_de_Datos(this, "bd", null, 1)
//                        db.profes()
                        menu.findItem(R.id.nav_profe).isVisible = true
                        menu.findItem(R.id.nav_cerrar_sesion).isVisible = false
                        fragment = RankingFragment()
                        replaceFragment(fragment, 1)
                    }//cerrar_sesion
                    R.id.nav_salir -> {
                        finishAndRemoveTask()
                    }//salir

                }//when(item.itemId)
            }//MainActivity
            "com.g2.santurtziapp.activitidades.JuegoActivity" -> {
                when(item.itemId){

                    R.id.nav_inicio -> {
                        startActivity(intent)
                        finish()
                    }//inicio
                    R.id.nav_ranking -> {
                        if (Sharedapp.tipousu.tipo.equals("profesor")){

                            intent.putExtra("fragment", "AnimacionCargaFragment()")
                            startActivity(intent)
                            finish()

                        }//is profesor
                        else {

                            intent.putExtra("fragment", "LogFragment()")
                            startActivity(intent)
                            finish()

                        }//is alumno
                    }//ranking
                    R.id.nav_quienes -> {
                        intent.putExtra("fragment", "QSFragment()")
                        startActivity(intent)
                        finish()
                    }//quienes
                    R.id.nav_alumno -> {
                        Sharedapp.users.user = ""
                        Sharedapp.tipousu.tipo = "alumno"
                    }//alumno
                    R.id.nav_profe -> {
                        intent.putExtra("fragment", "LogFragment()")
                        startActivity(intent)
                        finish()
                    }//profe
                    R.id.nav_cerrar_sesion -> {
                        Sharedapp.users.user = ""
                        Sharedapp.tipousu.tipo = "alumno"
                        startActivity(intent)
                        finish()
                    }//cerrar_sesion
                    R.id.nav_salir -> {
                        finishAndRemoveTask()
                    }//salir

                }//when(item.itemId)
            }//JuegoActivity

        }//when(activityName)

        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }//onNavigationItemSelected(item: MenuItem): Boolean

    //AL PULSAR HACIA ATRÁS
    override fun onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }//drawerLayout is open
        else{
            super.onBackPressed()
        }//drawerLayout is closed

    }//onBackPressed()

    //CAMBIAR FRAGMENTS
    internal fun replaceFragment(fragment: Fragment, f: Int){

        when(f){

            1 -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentMain, fragment).commit()
            }//1
            2 -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment1Juego, fragment).commit()
            }//2
            3 -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment2Juego, fragment).commit()
            }//3

        }//when(f)

    }//replaceFragment(fragment: Fragment, f: Int)

}//DrawerActivity()