package com.example.projetofinal

import android.content.ContentValues

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
}