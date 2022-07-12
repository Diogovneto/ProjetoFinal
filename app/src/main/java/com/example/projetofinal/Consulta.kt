package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns


class Consulta(
    var data: Long,
    var descricao: String,
    var preco: String,
    var id_medico: Long = -1,
    var id_paciente: Long = -1,
    var id_pulseira: Long = -1,
    var id: Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDConsultas.CAMPO_DATA, data)
        valores.put(TabelaBDConsultas.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaBDConsultas.CAMPO_PRECO, preco)
        valores.put(TabelaBDConsultas.CAMPO_MEDICO_ID, id_medico)
        valores.put(TabelaBDConsultas.CAMPO_PACIENTE_ID, id_paciente)
        valores.put(TabelaBDConsultas.CAMPO_PULSEIRA_ID, id_pulseira)


        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Consulta {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posData = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_DATA)
            val posDescricao = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_DESCRICAO)
            val posPreco = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_PRECO)
            val posIdMedico = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_MEDICO_ID)
            val posIdPaciente = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_PACIENTE_ID)
            val posIdPulseira = cursor.getColumnIndex(TabelaBDConsultas.CAMPO_PULSEIRA_ID)

            val id = cursor.getLong(posId)
            val data = cursor.getLong(posData)
            val descricao = cursor.getString(posDescricao)
            val preco = cursor.getString(posPreco)
            val id_medico = cursor.getLong(posIdMedico)
            val id_paciente = cursor.getLong(posIdPaciente)
            val id_pulseira = cursor.getLong(posIdPulseira)

            return Consulta(data, descricao, preco, id_medico, id_paciente,id_pulseira, id)
        }
    }

}

