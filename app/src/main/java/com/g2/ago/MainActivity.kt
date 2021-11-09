package com.g2.ago

import android.app.Activity
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
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.getItemId()){
            R.id.QS -> {
                startActivity(Intent(this, QSActivity::class.java))
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
    }


}