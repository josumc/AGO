package com.g2.ago

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Partida(var Nickname: String, var PuntoJuego: String)
class Base_de_Datos(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version:Int): SQLiteOpenHelper(context,name,factory, version){
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create table Partida(player text primary key, play_point text)")
        insertar("josu", "8", db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop table if exists Partida")
        onCreate(db)
    }
    //Funcion para insertar nuevos jugadores
    fun insertar (jugador: String, punto_partida:String, db: SQLiteDatabase?){
        //val db=this.writableDatabase
        val registrar=ContentValues()
        registrar.put("player", jugador)
        registrar.put("play_point", punto_partida)
        db?.insert("Partida", null, registrar)
    }
    //Funcion para cargar todos los datos
    fun Cargar ():ArrayList<Partidas> {
        val fila: ArrayList<Partidas> = ArrayList()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("select * from Partida", null)
        while (cursor.moveToNext()) {
            val todo = Partidas(cursor.getString(0),cursor.getString(1))
            fila.add(todo)
        }
        return fila
    }
    //Funcion para cargar los jugadores
    fun Cargar_jugadores (jugadores:String):MutableList<String>{
        val fila:MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val cursor:Cursor = db.rawQuery("select player from Partida", arrayOf(jugadores))
        while (cursor.moveToNext()){
            val todo = cursor.getString(0)
            fila.add(todo)
        }
        return fila
    }
    //Funcion para cargar los puntos de cada jugador
    fun Cargar_puntos (puntos:String):MutableList<String>{
        val fila:MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val cursor:Cursor = db.rawQuery("select play_point from Partida", arrayOf(puntos))
        while (cursor.moveToNext()){
            val todo = cursor.getString(0)
            fila.add(todo)
        }
        return fila
    }
    //Funcion para actualizar los puntos de juego
    fun actualizar(players: String, play_point: String, old_point: String){
        val db=this.writableDatabase
        val fila=ContentValues()
        fila.put("play_point", play_point)
        db.update("Partida", fila, "player=?", arrayOf(old_point))
    }
}
