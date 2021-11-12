package com.g2.ago

import androidx.fragment.app.Fragment

interface Comunicador {
    fun onPasarDato(dato:String)

    fun replaceFragment(fragment: Fragment)
}