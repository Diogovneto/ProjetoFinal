package com.example.projetofinal

import android.content.ContentValues

class Pulseira(var pulseira: String, var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDPulseiras.CAMPO_PULSEIRA, pulseira)

        return valores
    }
}