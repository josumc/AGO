package com.g2.ago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater

        if (Sharedapp.tipousu.tipo.equals("profesor")){
            inflater.inflate(R.menu.menu, menu)
        }else{
            inflater.inflate(R.menu.menualumno, menu)
        }
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.clasificacion -> {
               if (Sharedapp.tipousu.tipo.equals("profesor")){
                    //Activar fragment ranking
                }else{
                    //Activar fragment de profesor
                    Sharedapp.tipousu.tipo = "profesor"
                }
            }
            R.id.QS -> {
                startActivity(Intent(this, QSActivity::class.java))
            }
            R.id.CS -> {
                //Activar fragment y cerrar sesion
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
       Sharedapp.tipousu.tipo="alumno"

    }


}