package com.example.projetofinal

import android.content.ContentValues

class Paciente(
    var nome: String,
    var data_nascimento: Long,
    var sexo: String,
    var morada: String,
    var codigo_postal: Long,
    var telemovel: Long,
    var email: String,
    var cartao_cidadao: Long,
    var contribuinte: Long,
    var id: Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDPacientes.CAMPO_NOME, nome)
        valores.put(TabelaBDPacientes.CAMPO_DATA_NASCIMENTO, data_nascimento)
        valores.put(TabelaBDPacientes.CAMPO_SEXO, sexo)
        valores.put(TabelaBDPacientes.CAMPO_MORADA, morada)
        valores.put(TabelaBDPacientes.CAMPO_CODIGO_POSTAL, codigo_postal)
        valores.put(TabelaBDPacientes.CAMPO_TELEMOVEL, telemovel)
        valores.put(TabelaBDPacientes.CAMPO_EMAIL, email)
        valores.put(TabelaBDPacientes.CAMPO_CARTAO_CIDADAO, cartao_cidadao)
        valores.put(TabelaBDPacientes.CAMPO_CONTRIBUINTE, contribuinte)


        return valores
    }
}