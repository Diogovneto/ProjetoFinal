package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDConsultas(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $CAMPO_DATA DATE NOT NULL, " +
                "$CAMPO_TIPO TEXT NOT NULL, " +
                "$CAMPO_DESCRICAO TEXT NOT NULL, " +
                "$CAMPO_PRECO FLOAT NOT NULL, " +
                "$CAMPO_MEDICO_ID INTEGER NOT NULL, " +
                "$CAMPO_PACIENTE_ID INTEGER NOT NULL," +
                "FOREIGN KEY ($CAMPO_MEDICO_ID) REFERENCES ${TabelaBDMedicos.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT, " +
                "FOREIGN KEY ($CAMPO_PACIENTE_ID) REFERENCES ${TabelaBDPacientes.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME = "Consultas"
        const val CAMPO_DATA = "Data"
        const val CAMPO_TIPO = "Tipo"
        const val CAMPO_DESCRICAO = "Descricao"
        const val CAMPO_PRECO = "Preco"
        const val CAMPO_MEDICO_ID = "Medicoid"
        const val CAMPO_PACIENTE_ID = "Pacienteid"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID,
            CAMPO_DATA,
            CAMPO_TIPO,
            CAMPO_DESCRICAO,
            CAMPO_PRECO,
            CAMPO_MEDICO_ID,
            CAMPO_PACIENTE_ID)

    }
}