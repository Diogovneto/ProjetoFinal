package com.example.projetofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDConsultas(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$CAMPO_DATA INTEGER NOT NULL, " +
                "$CAMPO_DESCRICAO TEXT NOT NULL, " +
                "$CAMPO_PRECO TEXT NOT NULL, " +
                "$CAMPO_MEDICO_ID INTEGER NOT NULL, " +
                "$CAMPO_PACIENTE_ID INTEGER NOT NULL," +
                "$CAMPO_PULSEIRA_ID INTEGER NOT NULL," +
                "FOREIGN KEY ($CAMPO_MEDICO_ID) REFERENCES ${TabelaBDMedicos.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT, " +
                "FOREIGN KEY ($CAMPO_PACIENTE_ID) REFERENCES ${TabelaBDPacientes.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT," +
                "FOREIGN KEY ($CAMPO_PULSEIRA_ID) REFERENCES ${TabelaBDPulseiras.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilderConsultas = SQLiteQueryBuilder()
        queryBuilderConsultas.tables = "$NOME INNER JOIN ${TabelaBDMedicos.NOME} ON ${TabelaBDMedicos.NOME}.${BaseColumns._ID} = $CAMPO_MEDICO_ID INNER JOIN ${TabelaBDPacientes.NOME} ON ${TabelaBDPacientes.NOME}.${BaseColumns._ID} = $CAMPO_PACIENTE_ID INNER JOIN ${TabelaBDPulseiras.NOME} ON ${TabelaBDPulseiras.NOME}.${BaseColumns._ID} = $CAMPO_PULSEIRA_ID"

        return queryBuilderConsultas.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object {
        const val NOME = "Consultas"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_DATA = "Data"
        const val CAMPO_DESCRICAO = "Descricao"
        const val CAMPO_PRECO = "Preco"
        const val CAMPO_MEDICO_ID = "Medicoid"
        const val CAMPO_PACIENTE_ID = "Pacienteid"
        const val CAMPO_PULSEIRA_ID = "Pulseiraid"

        val TODAS_COLUNAS = arrayOf(
            CAMPO_ID,
            CAMPO_DATA,
            CAMPO_DESCRICAO,
            CAMPO_PRECO,
            CAMPO_MEDICO_ID,
            CAMPO_PACIENTE_ID,
            CAMPO_PULSEIRA_ID)

    }
}