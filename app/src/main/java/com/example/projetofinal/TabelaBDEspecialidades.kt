package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDEspecialidades (db: SQLiteDatabase) : TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $NOME(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_ESPECIALIDADE TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "Especialidades"
        const val CAMPO_ESPECIALIDADE = "Especialidade"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_ESPECIALIDADE)
    }
}