package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

class Especialidade(var especialidade: String, var id: Long = -1): Serializable {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()
        valores.put(TabelaBDEspecialidades.CAMPO_ESPECIALIDADE, especialidade)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Especialidade {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posEspecialidade = cursor.getColumnIndex(TabelaBDEspecialidades.CAMPO_ESPECIALIDADE)

            val id = cursor.getLong(posId)
            val especialidade = cursor.getString(posEspecialidade)

            return Especialidade(especialidade, id)
        }
    }
}