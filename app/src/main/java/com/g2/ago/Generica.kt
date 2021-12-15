package com.g2.ago

import android.content.Context
import androidx.fragment.app.Fragment

class Generica (context: Context) {
     fun replaceFragment(fragment: Fragment) {
         val activity = JuegoActivity()
        val transaction = activity.supportFragmentManager.beginTransaction()
         transaction.replace(R.id.FragmentMapaJuego, fragment)
         transaction.disallowAddToBackStack()
         transaction.commit()
     }
}