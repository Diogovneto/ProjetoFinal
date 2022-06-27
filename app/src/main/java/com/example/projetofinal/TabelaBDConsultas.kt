package com.example.projetofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
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

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilderMedicos = SQLiteQueryBuilder()
        queryBuilderMedicos.tables = "$NOME INNER JOIN ${TabelaBDMedicos.NOME} ON ${TabelaBDMedicos.NOME}.${BaseColumns._ID} = $CAMPO_MEDICO_ID"

        val queryBuilderPacientes = SQLiteQueryBuilder()
        queryBuilderPacientes.tables = "$NOME INNER JOIN ${TabelaBDPacientes.NOME} ON ${TabelaBDPacientes.NOME}.${BaseColumns._ID} = $CAMPO_PACIENTE_ID"

        return queryBuilderMedicos.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
        return queryBuilderPacientes.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object {
        const val NOME = "Consultas"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
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