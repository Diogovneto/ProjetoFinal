package com.example.projetofinal

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns


class Consulta(
    var data: Long,
    var descricao: String,
    var preco: String,
    var medico: Medico,
    var paciente: Paciente,
    var pulseira: Pulseira,
    var id: Long = -1) {

    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDConsultas.CAMPO_DATA, data)
        valores.put(TabelaBDConsultas.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaBDConsultas.CAMPO_PRECO, preco)
        valores.put(TabelaBDConsultas.CAMPO_MEDICO_ID, medico.id)
        valores.put(TabelaBDConsultas.CAMPO_PACIENTE_ID, paciente.id)
        valores.put(TabelaBDConsultas.CAMPO_PULSEIRA_ID, pulseira.id)




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

            val posNomeMedico = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_NOME)
            val posTelemovelMedico = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_TELEMOVEL)
            val posEmailMedico = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_EMAIL)
            val posSexoMedico = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_SEXO)
            val posCartaoCidadaoMedico = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_CARTAO_CIDADAO)
            val posIdEspecialidadeMedico = cursor.getColumnIndex(TabelaBDMedicos.CAMPO_ESPECIALIDADE_ID)
            val posNomeEspecialidadeMedico = cursor.getColumnIndex(TabelaBDEspecialidades.CAMPO_ESPECIALIDADE)

            val nome_medico = cursor.getString(posNomeMedico)
            val telemovel_medico = cursor.getString(posTelemovelMedico)
            val email_medico = cursor.getString(posEmailMedico)
            val sexo_medico = cursor.getString(posSexoMedico)
            val cartao_cidadao_medico = cursor.getString(posCartaoCidadaoMedico)
            val especialidade_id_medico = cursor.getLong(posIdEspecialidadeMedico)
            val nome_especialidade_medico = cursor.getString(posNomeEspecialidadeMedico)

            val posNomePaciente = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_NOME)
            val posDataNascimentoPaciente = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_DATA_NASCIMENTO)
            val posSexoPaciente = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_SEXO)
            val posMorada = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_MORADA)
            val posCodigoPostal = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CODIGO_POSTAL)
            val posTelemovelPaciente = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_TELEMOVEL)
            val posEmailPaciente = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_EMAIL)
            val posCartaoCidadaoPaciente = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CARTAO_CIDADAO)
            val posContribuinte = cursor.getColumnIndex(TabelaBDPacientes.CAMPO_CONTRIBUINTE)

            val nome_paciente = cursor.getString(posNomePaciente)
            val data_nascimento_paciente = cursor.getString(posDataNascimentoPaciente)
            val sexo_paciente = cursor.getString(posSexoPaciente)
            val morada = cursor.getString(posMorada)
            val codigo_postal = cursor.getString(posCodigoPostal)
            val telemovel_paciente = cursor.getString(posTelemovelPaciente)
            val email_paciente = cursor.getString(posEmailPaciente)
            val cartao_cidadao_paciente = cursor.getString(posCartaoCidadaoPaciente)
            val contribuinte = cursor.getString(posContribuinte)

            val posPulseiraConsulta = cursor.getColumnIndex(TabelaBDPulseiras.CAMPO_PULSEIRA)

            val pulseira_consulta = cursor.getString(posPulseiraConsulta)

            val especialidade_medico = Especialidade(nome_especialidade_medico, especialidade_id_medico)
            val medico = Medico(nome_medico, telemovel_medico, email_medico, sexo_medico, cartao_cidadao_medico, especialidade_medico, id_medico)
            val paciente = Paciente(nome_paciente, data_nascimento_paciente, sexo_paciente, morada, codigo_postal, telemovel_paciente, email_paciente, cartao_cidadao_paciente, contribuinte, id_paciente)
            val pulseira = Pulseira(pulseira_consulta, id_pulseira)

            return Consulta(data, descricao, preco, medico, paciente, pulseira, id)
        }
    }

}

