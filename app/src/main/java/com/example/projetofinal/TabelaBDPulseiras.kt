package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDPulseiras(val db: SQLiteDatabase) {
    fun cria(){
        db.execSQL("CREATE TABLE $NOME(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_PULSEIRA TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "pulseiras"
        const val CAMPO_PULSEIRA = "cor"
    }
}