package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns


class Consulta(
    var data: String,
    var tipo: String,
    var pulseira_paciente: String,
    var descricao: String,
    var preco: Long,
    var id_medico: Long = -1,
    var id_paciente: Long = -1,
    var id: Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDConsultas.CAMPO_DATA, data)
        valores.put(TabelaBDConsultas.CAMPO_TIPO, tipo)
        valores.put(TabelaBDConsultas.CAMPO_PULSEIRA_PACIENTE, pulseira_paciente)
        valores.put(TabelaBDConsultas.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaBDConsultas.CAMPO_PRECO, preco)
        valores.put(TabelaBDConsultas.CAMPO_MEDICO_ID, id_medico)
        valores.put(TabelaBDConsultas.CAMPO_PACIENTE_ID, id_paciente)


        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Consulta {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posData = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_DATA)
            val posTipo = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_TIPO)
            val posPulseiraPaciente = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_PULSEIRA_PACIENTE)
            val posDescricao = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_DESCRICAO)
            val posPreco = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_PRECO)
            val posIdMedico = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_MEDICO_ID)
            val posIdPaciente = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_PACIENTE_ID)

            val id = cursor.getLong(posId)
            val data = cursor.getString(posData)
            val tipo = cursor.getString(posTipo)
            val pulseira_paciente = cursor.getString(posPulseiraPaciente)
            val descricao = cursor.getString(posDescricao)
            val preco = cursor.getLong(posPreco)
            val id_medico = cursor.getLong(posIdMedico)
            val id_paciente = cursor.getLong(posIdPaciente)

            return Consulta(data, tipo, pulseira_paciente, descricao, preco, id_medico, id_paciente, id)
        }
    }

}

