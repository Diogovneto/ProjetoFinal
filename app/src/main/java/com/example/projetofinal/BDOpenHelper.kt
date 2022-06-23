package com.example.projetofinal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDOpenHelper (context: Context?) : SQLiteOpenHelper(context, NOME, null, VERSAO) {

    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)

        TabelaBDMedicos(db).cria()
        TabelaBDConsultas(db).cria()
        TabelaBDPacientes(db).cria()
        TabelaBDPulseiras(db).cria()

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        const val NOME = "ProjetoFinal.db"
        private const val VERSAO = 1
    }
}