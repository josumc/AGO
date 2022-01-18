package com.g2.ago

import android.content.Context
import android.content.SharedPreferences

class Atras (context: Context) {
    val PREFS_NAME = "com.g2.ago.sharedpreferences.Atras"
    val SHARED_NAME = "Atras"
    val Atras: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var atras: String
        //comprobamos el archivo de shared preferences
        get() = Atras.getString(SHARED_NAME, "").toString()
        //modificamos el valor de shared preferences
        set(value) = Atras.edit().putString(SHARED_NAME, value).apply()
}