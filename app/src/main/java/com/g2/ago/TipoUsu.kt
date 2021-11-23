package com.g2.ago

import android.content.Context
import android.content.SharedPreferences

class TipoUsu (context: Context) {
        val PREFS_NAME = "com.g2.ago.sharedpreferences"
        val SHARED_NAME = "Tipo"
        val name: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        var tipo: String
            //comprobamos el archivo de shared preferences
            get() = name.getString(SHARED_NAME, "").toString()
            //modificamos el valor de shared preferences
            set(value) = name.edit().putString(SHARED_NAME, value).apply()
}
