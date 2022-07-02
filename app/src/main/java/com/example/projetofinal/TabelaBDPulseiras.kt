package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDPulseiras(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria(){
        db.execSQL("CREATE TABLE $NOME(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_PULSEIRA TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "pulseiras"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_PULSEIRA = "cor"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_PULSEIRA)
    }
}