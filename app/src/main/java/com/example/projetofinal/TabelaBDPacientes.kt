package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDPacientes (val db: SQLiteDatabase) {

    fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_NOME TEXT NOT NULL," +
                " $CAMPO_DATA_NASCIMENTO DATE NOT NULL, " +
                "$CAMPO_SEXO TEXT NOT NULL, " +
                "$CAMPO_MORADA TEXT NOT NULL, " +
                "$CAMPO_CODIGO_POSTAL INTEGER NOT NULL, " +
                "$CAMPO_TELEMOVEL INTEGER NOT NULL, " +
                "$CAMPO_EMAIL TEXT NOT NULL, " +
                "$CAMPO_CARTAO_CIDADAO INTEGER NOT NULL, " +
                "$CAMPO_CONTRIBUINTE INTEGER NOT NULL)")
    }

    companion object {
        const val NOME = "Pacientes"
        const val CAMPO_NOME = "Nome"
        const val CAMPO_DATA_NASCIMENTO = "DataNascimento"
        const val CAMPO_SEXO = "Sexo"
        const val CAMPO_MORADA = "Morada"
        const val CAMPO_CODIGO_POSTAL = "CodigoPostal"
        const val CAMPO_TELEMOVEL = "Telemovel"
        const val CAMPO_EMAIL = "Email"
        const val CAMPO_CARTAO_CIDADAO = "CartaoCidadao"
        const val CAMPO_CONTRIBUINTE = "Contribuinte"
    }
}