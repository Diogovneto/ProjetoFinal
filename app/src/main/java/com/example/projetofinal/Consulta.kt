package com.example.projetofinal

import android.content.ContentValues


class Consulta(
    var data: String,
    var tipo: String,
    var pulseira_paciente: String,
    var descricao: String,
    var preco: Long,
    var id: Long = -1,
    var id2: Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDConsultas.CAMPO_DATA, data)
        valores.put(TabelaBDConsultas.CAMPO_TIPO, tipo)
        valores.put(TabelaBDConsultas.CAMPO_PULSEIRA_PACIENTE, pulseira_paciente)
        valores.put(TabelaBDConsultas.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaBDConsultas.CAMPO_PRECO, preco)
        valores.put(TabelaBDConsultas.CAMPO_MEDICO_ID, id)
        valores.put(TabelaBDConsultas.CAMPO_PACIENTE_ID, id2)


        return valores
    }
}

