package com.example.projetofinal

import android.content.ContentValues


class Consulta(
    var data: Long,
    var tipo: String,
    var pulseira_paciente: String,
    var descricao: String,
    var preco: Float,
    var id: Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDConsultas.CAMPO_DATA, data)
        valores.put(TabelaBDConsultas.CAMPO_TIPO, tipo)
        valores.put(TabelaBDConsultas.CAMPO_PULSEIRA_PACIENTE, pulseira_paciente)
        valores.put(TabelaBDConsultas.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaBDConsultas.CAMPO_PRECO, preco)


        return valores
    }
}
