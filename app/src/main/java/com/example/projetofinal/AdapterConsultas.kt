package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterConsultas(val fragment: ListaConsultasFragment) : RecyclerView.Adapter<AdapterConsultas.ViewHolderConsultas>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    class ViewHolderConsultas(itemConsulta: View) : RecyclerView.ViewHolder(itemConsulta) {
        val textViewDataConsulta = itemConsulta.findViewById<TextView>(R.id.textViewData)
        val textViewDescricaoConsulta = itemConsulta.findViewById<TextView>(R.id.textViewDescricao)
        val textViewPrecoConsulta = itemConsulta.findViewById<TextView>(R.id.textViewPreco)
        val textViewMedico = itemConsulta.findViewById<TextView>(R.id.textViewPulseira)
        val textViewMedicoConsulta = itemConsulta.findViewById<TextView>(R.id.textViewMedicoConsulta)
        val textViewPaciente = itemConsulta.findViewById<TextView>(R.id.textViewMedico)
        val textViewPacienteConsulta = itemConsulta.findViewById<TextView>(R.id.textViewPacienteConsulta)
        val textViewPulseira = itemConsulta.findViewById<TextView>(R.id.textViewPulseira)
        val textViewPulseiraConsulta = itemConsulta.findViewById<TextView>(R.id.textViewPulseiraParaConsulta)

        var consulta: Consulta? = null
            get() = field
            set(value) {
                field = value

                textViewDataConsulta.text = consulta?.data.toString()
                textViewDescricaoConsulta.text = consulta?.descricao ?: ""
                textViewPrecoConsulta.text = consulta?.preco ?: ""
                textViewMedico.text = "Médico"
                textViewMedicoConsulta.text = consulta?.medico?.nome ?: ""
                textViewPaciente.text = "Paciente"
                textViewPacienteConsulta.text = consulta?.paciente?.nome ?: ""
                textViewPulseira.text = "Pulseira"
                textViewPulseiraConsulta.text = consulta?.pulseira?.pulseira ?: ""
            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderConsultas {
        val itemConsulta = fragment.layoutInflater.inflate(R.layout.item_consulta, parent, false)
        return ViewHolderConsultas(itemConsulta)
    }


    override fun onBindViewHolder(holder: ViewHolderConsultas, position: Int) {
        cursor!!.moveToPosition(position)
        holder.consulta = Consulta.fromCursor(cursor!!)
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}