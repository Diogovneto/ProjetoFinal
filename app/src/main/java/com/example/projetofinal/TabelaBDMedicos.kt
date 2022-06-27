package com.example.projetofinal

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDMedicos(db: SQLiteDatabase) : TabelaBD(db, NOME) {

    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_NOME TEXT NOT NULL, " +
                "$CAMPO_TELEMOVEL INTEGER NOT NULL, " +
                "$CAMPO_EMAIL TEXT NOT NULL, " +
                "$CAMPO_SEXO TEXT NOT NULL, " +
                "$CAMPO_CARTAO_CIDADAO INTEGER NOT NULL," +
                "$CAMPO_ESPECIALIDADE_ID INTEGER NOT NULL," +
                "FOREIGN KEY ($CAMPO_ESPECIALIDADE_ID) REFERENCES ${TabelaBDEspecialidades.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
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
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDEspecialidades.NOME} ON ${TabelaBDEspecialidades.NOME}.${BaseColumns._ID} = ${CAMPO_ESPECIALIDADE_ID}"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NOME = "medicos"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_NOME = "nome"
        const val CAMPO_TELEMOVEL = "telemovel"
        const val CAMPO_EMAIL = "email"
        const val CAMPO_SEXO = "sexo"
        const val CAMPO_CARTAO_CIDADAO = "cartaocidadao"
        const val CAMPO_ESPECIALIDADE_ID = "Especialidadeid"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME, CAMPO_TELEMOVEL, CAMPO_EMAIL, CAMPO_SEXO, CAMPO_CARTAO_CIDADAO, CAMPO_ESPECIALIDADE_ID, TabelaBDEspecialidades.CAMPO_ESPECIALIDADE)
    }
}


