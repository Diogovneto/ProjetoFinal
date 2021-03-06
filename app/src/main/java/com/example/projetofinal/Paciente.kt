package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

class Paciente(
    var nome: String,
    var data_nascimento: String,
    var genero: String,
    var morada: String,
    var codigo_postal: String,
    var telemovel: String,
    var email: String,
    var cartao_cidadao: String,
    var contribuinte: String,
    var id: Long = -1): Serializable {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDPacientes.CAMPO_NOME, nome)
        valores.put(TabelaBDPacientes.CAMPO_DATA_NASCIMENTO, data_nascimento)
        valores.put(TabelaBDPacientes.CAMPO_GENERO, genero)
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
            val posGenero = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_GENERO)
            val posMorada = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_MORADA)
            val posCodigoPostal = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CODIGO_POSTAL)
            val postelemovel = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_TELEMOVEL)
            val posEmail = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_EMAIL)
            val posCartaoCidadao = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CARTAO_CIDADAO)
            val posContribuinte = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CONTRIBUINTE)


            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val data_nascimento = cursor.getString(posDataNascimento)
            val genero = cursor.getString(posGenero)
            val morada = cursor.getString(posMorada)
            val codigo_postal = cursor.getString(posCodigoPostal)
            val telemovel = cursor.getString(postelemovel)
            val email = cursor.getString(posEmail)
            val cartao_cidadao = cursor.getString(posCartaoCidadao)
            val contribuinte = cursor.getString(posContribuinte)

            return Paciente(nome, data_nascimento, genero, morada, codigo_postal, telemovel, email, cartao_cidadao, contribuinte, id)
        }
    }

}