package com.g2.ago

import android.app.Application

class Sharedapp : Application() {
    companion object{
        lateinit var users: User
        lateinit var tipousu: TipoUsu
    }

    override fun onCreate() {
        super.onCreate()

        users = User(applicationContext)
        tipousu = TipoUsu(applicationContext)
    }
}