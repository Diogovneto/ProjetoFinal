package com.example.projetofinal

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class ContentProviderConsultas : ContentProvider() {
    var db : BDOpenHelper? = null

    override fun onCreate(): Boolean {
        db = BDOpenHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    companion object {
        const val AUTHORITY = "com.example.projetofinal"

        const val URI_MEDICOS = 100
        const val URI_MEDICO_ESPECIFICO = 101
        const val URI_PACIENTES = 200
        const val URI_PACIENTE_ESPECIFICO = 201
        const val URI_CONSULTAS = 300
        const val URI_CONSULTA_ESPECIFICA = 301
        const val URI_PULSEIRAS = 400
        const val URI_PULSEIRA_ESPECIFICA = 401
        const val URI_ESPECIALIDADES = 500
        const val URI_ESPECIALIDADE_ESPECIFICA = 501

        fun getUriMatcher() : UriMatcher {
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, TabelaBDMedicos.NOME, URI_MEDICOS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDMedicos.NOME}/#", URI_MEDICO_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY, TabelaBDPacientes.NOME, URI_PACIENTES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDPacientes.NOME}/#", URI_PACIENTE_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY, TabelaBDConsultas.NOME, URI_CONSULTAS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDConsultas.NOME}/#", URI_CONSULTA_ESPECIFICA)
            uriMatcher.addURI(AUTHORITY, TabelaBDPulseiras.NOME, URI_PULSEIRAS)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDPulseiras.NOME}/#", URI_PULSEIRA_ESPECIFICA)
            uriMatcher.addURI(AUTHORITY, TabelaBDEspecialidades.NOME, URI_ESPECIALIDADES)
            uriMatcher.addURI(AUTHORITY, "${TabelaBDEspecialidades.NOME}/#", URI_ESPECIALIDADE_ESPECIFICA)

            return uriMatcher
        }
    }

}