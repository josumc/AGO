package com.g2.ago

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.ContextCompat.startActivity

class User (context: Context) {
    val PREFS_NAME = "com.g2.ago.sharedpreferences.User"
    val SHARED_NAME = "shared_name"
    val User: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        var user: String
            //comprobamos el archivo de shared preferences
            get() = User.getString(SHARED_NAME, "").toString()
            //modificamos el valor de shared preferences
            set(value) = User.edit().putString(SHARED_NAME, value).apply()
}

