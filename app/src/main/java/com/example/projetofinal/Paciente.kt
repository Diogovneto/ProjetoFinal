package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Paciente(
    var nome: String,
    var data_nascimento: String,
    var sexo: String,
    var morada: String,
    var codigo_postal: String,
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

    companion object {
        fun fromCursor(cursor: Cursor): Paciente {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_NOME)
            val posDataNascimento = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_DATA_NASCIMENTO)
            val posSexo = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_SEXO)
            val posMorada = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_MORADA)
            val posCodigoPostal = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CODIGO_POSTAL)
            val postelemovel = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_TELEMOVEL)
            val posEmail = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_EMAIL)
            val posCartaoCidadao = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CARTAO_CIDADAO)
            val posContribuinte = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CONTRIBUINTE)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val data_nascimento = cursor.getString(posDataNascimento)
            val sexo = cursor.getString(posSexo)
            val morada = cursor.getString(posMorada)
            val codigo_postal = cursor.getString(posCodigoPostal)
            val telemovel = cursor.getLong(postelemovel)
            val email = cursor.getString(posEmail)
            val cartao_cidadao = cursor.getLong(posCartaoCidadao)
            val contribuinte = cursor.getLong(posContribuinte)

            return Paciente(nome, data_nascimento, sexo, morada, codigo_postal, telemovel, email, cartao_cidadao, contribuinte, id)
        }
    }

}