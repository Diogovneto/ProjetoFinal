package com.example.projetofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDPacientes (db: SQLiteDatabase) : TabelaBD(db, NOME) {

    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_NOME TEXT NOT NULL," +
                " $CAMPO_DATA_NASCIMENTO TEXT NOT NULL, " +
                "$CAMPO_SEXO TEXT NOT NULL, " +
                "$CAMPO_MORADA TEXT NOT NULL, " +
                "$CAMPO_CODIGO_POSTAL TEXT NOT NULL, " +
                "$CAMPO_TELEMOVEL TEXT NOT NULL, " +
                "$CAMPO_EMAIL TEXT NOT NULL, " +
                "$CAMPO_CARTAO_CIDADAO TEXT NOT NULL, " +
                "$CAMPO_CONTRIBUINTE TEXT NOT NULL")

    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDPulseiras.NOME} ON ${TabelaBDPulseiras.NOME}.${BaseColumns._ID}"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object {
        const val NOME = "Pacientes"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_NOME = "Nome"
        const val CAMPO_DATA_NASCIMENTO = "DataNascimento"
        const val CAMPO_SEXO = "Sexo"
        const val CAMPO_MORADA = "Morada"
        const val CAMPO_CODIGO_POSTAL = "CodigoPostal"
        const val CAMPO_TELEMOVEL = "Telemovel"
        const val CAMPO_EMAIL = "Email"
        const val CAMPO_CARTAO_CIDADAO = "CartaoCidadao"
        const val CAMPO_CONTRIBUINTE = "Contribuinte"

        val TODAS_COLUNAS = arrayOf(
            CAMPO_ID,
            CAMPO_NOME,
            CAMPO_DATA_NASCIMENTO,
            CAMPO_SEXO, CAMPO_MORADA,
            CAMPO_CODIGO_POSTAL,
            CAMPO_TELEMOVEL,
            CAMPO_EMAIL,
            CAMPO_CARTAO_CIDADAO,
            CAMPO_CONTRIBUINTE)

    }
}