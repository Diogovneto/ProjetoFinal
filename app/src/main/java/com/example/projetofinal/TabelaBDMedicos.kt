package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDMedicos(val db: SQLiteDatabase) {

    fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_NOME TEXT NOT NULL, " +
                "$CAMPO_ESPECIALIDADE TEXT NOT NULL, " +
                "$CAMPO_TELEMOVEL INTEGER NOT NULL, " +
                "$CAMPO_EMAIL TEXT NOT NULL, " +
                "$CAMPO_SEXO TEXT NOT NULL, " +
                "$CAMPO_CARTAO_CIDADAO INTEGER NOT NULL)")
    }

    companion object{
        const val NOME = "medicos"
        const val CAMPO_NOME = "nome"
        const val CAMPO_ESPECIALIDADE = "especialidade"
        const val CAMPO_TELEMOVEL = "telemovel"
        const val CAMPO_EMAIL = "email"
        const val CAMPO_SEXO = "sexo"
        const val CAMPO_CARTAO_CIDADAO = "cartaocidadao"
    }
}


