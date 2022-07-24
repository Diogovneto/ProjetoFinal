package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Medico(
    var nome: String? = null,
    var telemovel: String,
    var email: String,
    var genero: String,
    var cartao_cidadao: String,
    var especialidade: Especialidade,
    var id: Long = -1
): Serializable {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDMedicos.CAMPO_NOME, nome)
        valores.put(TabelaBDMedicos.CAMPO_TELEMOVEL, telemovel)
        valores.put(TabelaBDMedicos.CAMPO_EMAIL, email)
        valores.put(TabelaBDMedicos.CAMPO_GENERO, genero)
        valores.put(TabelaBDMedicos.CAMPO_CARTAO_CIDADAO, cartao_cidadao)
        valores.put(TabelaBDMedicos.CAMPO_ESPECIALIDADE_ID, especialidade.id)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Medico {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_NOME)
            val posTelemovel = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_TELEMOVEL)
            val posEmail = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_EMAIL)
            val posGenero = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_GENERO)
            val posCartaoCidadao = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_CARTAO_CIDADAO)
            val posIdEspecialidade = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_ESPECIALIDADE_ID)
            val posNomeEspecialidade = cursor.getColumnIndex(TabelaBDEspecialidades.CAMPO_ESPECIALIDADE)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val telemovel = cursor.getString(posTelemovel)
            val email = cursor.getString(posEmail)
            val genero = cursor.getString(posGenero)
            val cartao_cidadao = cursor.getString(posCartaoCidadao)
            val id_especialidade = cursor.getLong(posIdEspecialidade)
            val nomeEspecialidade = cursor.getString(posNomeEspecialidade)

            val especialidade = Especialidade(nomeEspecialidade, id_especialidade)

            return Medico(nome, telemovel, email, genero, cartao_cidadao,especialidade, id)
        }
    }

}