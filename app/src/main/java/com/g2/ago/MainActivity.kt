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

class MainActivity : DrawerActivity(), NavigationView.OnNavigationItemSelectedListener, Comunicador {

    lateinit var binding: ActivityMainBinding
    private lateinit var db:Base_de_Datos

    override fun onCreate(savedInstanceState: Bundle?) {
        //sleep(1000)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.root.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        var fr = intent.extras

        if (fr != null) {
            when(fr.get("fragment")){
                "LogFragment()" -> replaceFragment(LogFragment())
                "QSFragment()" -> replaceFragment(QSFragment())
                "RankingFragment()" -> replaceFragment(RankingFragment())
            }
        }
    }

    override fun onPasarDato(dato: String) {

    }

    override fun activarBoton(dato: Boolean) {

    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragmentMain, fragment).commit()
    }

}