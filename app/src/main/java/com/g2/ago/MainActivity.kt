package com.g2.ago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater

        if (Sharedapp.tipousu.tipo.equals("profesor")){
            inflater.inflate(R.menu.menu, menu)
        }else if (Sharedapp.tipousu.tipo.equals("alumno")){
            inflater.inflate(R.menu.menualumno, menu)
        }
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.clasificacion -> {
               if (Sharedapp.tipousu.tipo.equals("profesor")){
                    //Activar fragment ranking
                }else if (Sharedapp.tipousu.tipo.equals("alumno")){
                    //Activar fragment de profesor
                    Sharedapp.tipousu.tipo = "profesor"
                   finish()
                   startActivity(Intent(this, MainActivity::class.java))


                }
            }
            R.id.QS -> {
                startActivity(Intent(this, QSActivity::class.java))
            }
            R.id.CS -> {
                //Activar fragment

                //Se cierra la sesion
                Sharedapp.users.user = ""
                Sharedapp.tipousu.tipo = "alumno"
            }
            R.id.Salir -> {
                finishAndRemoveTask()
            }
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        SystemClock.sleep(1000)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Sharedapp.tipousu.tipo.equals("profesor")){
            val x : Fragment = LogFragment()
            val transacion = supportFragmentManager.beginTransaction().add(R.id.fragmentMapas, x).addToBackStack(null).commit()
        }else if (Sharedapp.tipousu.tipo.equals("alumno")){
            val y : Fragment = fragment2()
            val transacion = supportFragmentManager.beginTransaction().add(R.id.fragmentMapas, y).addToBackStack(null).commit()
        }

    }


}