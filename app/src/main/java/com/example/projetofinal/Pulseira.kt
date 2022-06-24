package com.example.projetofinal

import android.content.ContentValues

class Pulseira(var id: Long,
               var pulseira: String) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDPulseiras.CAMPO_PULSEIRA, pulseira)

        return valores
    }
}