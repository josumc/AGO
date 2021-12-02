package com.g2.ago

import android.content.Context
import android.content.SharedPreferences

class ModoLibre(context: Context)  {
    val PREFS_NAME = "com.g2.ago.sharedpreferences"
    val SHARED_NAME = "Modo"
    val name: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var modo: Boolean
        //comprobamos el archivo de shared preferences
        get() = name.getBoolean(SHARED_NAME, false)
        //modificamos el valor de shared preferences
        set(value) = name.edit().putBoolean(SHARED_NAME, value).apply()
}
