package com.example.projetofinal

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class ContentProviderConsultas : ContentProvider() {
    var dbOpenH : BDOpenHelper? = null

    override fun onCreate(): Boolean {
        dbOpenH = BDOpenHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbOpenH!!.readableDatabase

        val colunas = projection as Array<String>
        val selArgs = selectionArgs as Array<String>

        val id = uri.lastPathSegment

        return when (getUriMatcher().match(uri)) {
            URI_MEDICOS -> TabelaBDMedicos(db).query(colunas, selection, selArgs,null,null, sortOrder)
            URI_PACIENTES -> TabelaBDPacientes(db).query(colunas, selection, selArgs, null, null, sortOrder)
            URI_CONSULTAS -> TabelaBDConsultas(db).query(colunas, selection, selArgs, null, null, sortOrder)
            URI_PULSEIRAS -> TabelaBDPulseiras(db).query(colunas, selection, selArgs, null, null, sortOrder)
            URI_ESPECIALIDADES -> TabelaBDEspecialidades(db).query(colunas, selection, selArgs, null, null, sortOrder)
            URI_MEDICO_ESPECIFICO -> TabelaBDMedicos(db).query(colunas, "${BaseColumns._ID}=?", arrayOf("$id"), null, null, null)
            URI_PACIENTE_ESPECIFICO -> TabelaBDPacientes(db).query(colunas,"${BaseColumns._ID}=?", arrayOf("$id"), null, null, null)
            URI_CONSULTA_ESPECIFICA -> TabelaBDConsultas(db).query(colunas,"${BaseColumns._ID}=?", arrayOf("$id"), null, null, null)
            URI_PULSEIRA_ESPECIFICA -> TabelaBDPulseiras(db).query(colunas,"${BaseColumns._ID}=?", arrayOf("$id"), null, null, null)
            URI_ESPECIALIDADE_ESPECIFICA -> TabelaBDEspecialidades(db).query(colunas,"${BaseColumns._ID}=?", arrayOf("$id"), null, null, null)
            else -> null

        }
    }

    override fun getType(uri: Uri): String? =
        when(getUriMatcher().match(uri)) {
            URI_MEDICOS -> "$MULTIPLOS_REGISTOS/${TabelaBDMedicos.NOME}"
            URI_PACIENTES -> "$MULTIPLOS_REGISTOS/${TabelaBDPacientes.NOME}"
            URI_CONSULTAS -> "$MULTIPLOS_REGISTOS/${TabelaBDConsultas.NOME}"
            URI_PULSEIRAS -> "$MULTIPLOS_REGISTOS/${TabelaBDPulseiras.NOME}"
            URI_ESPECIALIDADES -> "$MULTIPLOS_REGISTOS/${TabelaBDEspecialidades.NOME}"
            URI_MEDICO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDMedicos.NOME}"
            URI_PACIENTE_ESPECIFICO -> "$UNICO_REGISTO/${TabelaBDPacientes.NOME}"
            URI_CONSULTA_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDConsultas.NOME}"
            URI_PULSEIRA_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDPulseiras.NOME}"
            URI_ESPECIALIDADE_ESPECIFICA -> "$UNICO_REGISTO/${TabelaBDEspecialidades.NOME}"
            else -> null
        }



    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbOpenH!!.writableDatabase

        requireNotNull(values)

        val id = when(getUriMatcher().match(uri)){
            URI_MEDICOS -> TabelaBDMedicos(db).insert(values)
            URI_PACIENTES -> TabelaBDPacientes(db).insert(values)
            URI_CONSULTAS -> TabelaBDConsultas(db).insert(values)
            URI_PULSEIRAS -> TabelaBDPulseiras(db).insert(values)
            URI_ESPECIALIDADES -> TabelaBDEspecialidades(db).insert(values)
            else -> -1
        }

        if (id == -1L) return null

        return Uri.withAppendedPath(uri, "$id")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbOpenH!!.writableDatabase

        val id = uri.lastPathSegment

        return when(getUriMatcher().match(uri)) {
            URI_MEDICO_ESPECIFICO -> TabelaBDMedicos(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            URI_PACIENTE_ESPECIFICO -> TabelaBDPacientes(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            URI_CONSULTA_ESPECIFICA -> TabelaBDConsultas(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            URI_PULSEIRA_ESPECIFICA -> TabelaBDPulseiras(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            URI_ESPECIALIDADE_ESPECIFICA -> TabelaBDEspecialidades(db).delete("${BaseColumns._ID}=?", arrayOf("$id"))
            else -> 0
        }
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

        private const val UNICO_REGISTO = "vnd.android.cursor.item"
        private const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

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