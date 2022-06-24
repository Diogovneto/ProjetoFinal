package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

class Pulseira(var pulseira: String, var id: Long = -1) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDPulseiras.CAMPO_PULSEIRA, pulseira)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Pulseira {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posPulseira = cursor.getColumnIndex(TabelaBDPulseiras.CAMPO_PULSEIRA)

            val id = cursor.getLong(posId)
            val pulseira = cursor.getString(posPulseira)

            return Pulseira(pulseira, id)
        }
    }

}