package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDConsultas(val db: SQLiteDatabase) {
    fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_DATA DATE NOT NULL, $CAMPO_TIPO TEXT NOT NULL, $CAMPO_DESCRICAO TEXT NOT NULL, $CAMPO_PULSEIRA_PACIENTE TEXT NOT NULL, $CAMPO_PRECO FLOAT NOT NULL, $CAMPO_MEDICO_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_MEDICO_ID) REFERENCES ${TabelaBDMedicos.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME = "Consultas"
        const val CAMPO_DATA = "Data"
        const val CAMPO_TIPO = "Tipo"
        const val CAMPO_DESCRICAO = "Descricao"
        const val CAMPO_PULSEIRA_PACIENTE = "PulseiraPaciente"
        const val CAMPO_PRECO = "Preco"
        const val CAMPO_MEDICO_ID = "Medicoid"
    }
}