package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Medico(
    var nome: String,
    var especialidade: String,
    var telemovel: Long,
    var email: String,
    var sexo: String,
    var cartao_cidadao: Long,
    var id: Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDMedicos.CAMPO_NOME, nome)
        valores.put(TabelaBDMedicos.CAMPO_ESPECIALIDADE, especialidade)
        valores.put(TabelaBDMedicos.CAMPO_TELEMOVEL, telemovel)
        valores.put(TabelaBDMedicos.CAMPO_EMAIL, email)
        valores.put(TabelaBDMedicos.CAMPO_SEXO, sexo)
        valores.put(TabelaBDMedicos.CAMPO_CARTAO_CIDADAO, cartao_cidadao)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Medico {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_NOME)
            val posEspecialidade = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_ESPECIALIDADE)
            val posTelemovel = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_TELEMOVEL)
            val posEmail = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_EMAIL)
            val posSexo = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_SEXO)
            val posCartaoCidadao = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_CARTAO_CIDADAO)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val especialidade = cursor.getString(posEspecialidade)
            val telemovel = cursor.getLong(posTelemovel)
            val email = cursor.getString(posEmail)
            val sexo = cursor.getString(posSexo)
            val cartao_cidadao = cursor.getLong(posCartaoCidadao)

            return Medico(nome, especialidade, telemovel, email, sexo, cartao_cidadao, id)
        }
    }

}