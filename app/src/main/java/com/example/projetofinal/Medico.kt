package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Medico(
    var nome: String,
    var telemovel: Long,
    var email: String,
    var sexo: String,
    var cartao_cidadao: Long,
    var id_especialidade: Long = -1,
    var id: Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDMedicos.CAMPO_NOME, nome)
        valores.put(TabelaBDMedicos.CAMPO_TELEMOVEL, telemovel)
        valores.put(TabelaBDMedicos.CAMPO_EMAIL, email)
        valores.put(TabelaBDMedicos.CAMPO_SEXO, sexo)
        valores.put(TabelaBDMedicos.CAMPO_CARTAO_CIDADAO, cartao_cidadao)
        valores.put(TabelaBDMedicos.CAMPO_ESPECIALIDADE_ID, id_especialidade)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Medico {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_NOME)
            val posTelemovel = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_TELEMOVEL)
            val posEmail = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_EMAIL)
            val posSexo = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_SEXO)
            val posCartaoCidadao = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_CARTAO_CIDADAO)
            val posIdEspecialidade = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_ESPECIALIDADE_ID)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val telemovel = cursor.getLong(posTelemovel)
            val email = cursor.getString(posEmail)
            val sexo = cursor.getString(posSexo)
            val cartao_cidadao = cursor.getLong(posCartaoCidadao)
            val id_especialidade = cursor.getLong(posIdEspecialidade)

            return Medico(nome, telemovel, email, sexo, cartao_cidadao,id_especialidade, id)
        }
    }

}