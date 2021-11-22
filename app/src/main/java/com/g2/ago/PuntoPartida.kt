package com.g2.ago

import android.content.Context
import android.content.SharedPreferences

class PuntoPartida (context: Context) {
    val PREFS_NAME = "com.g2.ago.sharedpreferences.PuntoPartida"
    val SHARED_NAME = "shared_name"
    val PuntoPartida: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var Partida: String
        //comprobamos el archivo de shared preferences
        get() = PuntoPartida.getString(SHARED_NAME, "").toString()
        //modificamos el valor de shared preferences
        set(value) = PuntoPartida.edit().putString(SHARED_NAME, value).apply()
}