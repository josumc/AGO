package com.g2.ago

import android.app.Application

class Sharedapp : Application() {
    companion object{
        lateinit var users: User
        lateinit var tipousu: TipoUsu
        lateinit var puntopartida: PuntoPartida
        lateinit var puntojuego: PuntoJuego
    }

    override fun onCreate() {
        super.onCreate()

        users = User(applicationContext)
        tipousu = TipoUsu(applicationContext)
        puntopartida = PuntoPartida(applicationContext)
        puntojuego = PuntoJuego(applicationContext)
    }
}