package com.g2.ago

import android.content.Context
import android.content.SharedPreferences

class PuntoJuego (context: Context) {
    val PREFS_NAME = "com.g2.ago.sharedpreferences.PuntoJuegp"
    val SHARED_NAME = "Juego"
    val PuntoJuego: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var Juego: String
        //comprobamos el archivo de shared preferences
        get() = PuntoJuego.getString(SHARED_NAME, "").toString()
        //modificamos el valor de shared preferences
        set(value) = PuntoJuego.edit().putString(SHARED_NAME, value).apply()
}