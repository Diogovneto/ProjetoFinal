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
        val textViewPulseiraConsultaTitulo = itemConsulta.findViewById<TextView>(R.id.textViewPulseiraConsultaTitulo)
        val textViewPulseiraConsultaItem = itemConsulta.findViewById<TextView>(R.id.textViewPulseiraConsultaItem)
        val textViewMedicoConsultaTitulo = itemConsulta.findViewById<TextView>(R.id.textViewMedicoConsultaTitulo)
        val textViewMedicoConsultaItem = itemConsulta.findViewById<TextView>(R.id.textViewMedicoConsultaItem)
        val textViewPacienteConsultaTitulo = itemConsulta.findViewById<TextView>(R.id.textViewPacienteConsultaTitulo)
        val textViewPacienteConsultaItem = itemConsulta.findViewById<TextView>(R.id.textViewPacienteConsultaItem)

        var consulta: Consulta? = null
            get() = field
            set(value) {
                field = value

                textViewDataConsulta.text = consulta?.data ?: ""
                textViewDescricaoConsulta.text = consulta?.descricao ?: ""
                textViewPrecoConsulta.text = consulta?.preco ?: ""
                textViewPulseiraConsultaTitulo.text = "Pulseira"
                textViewPulseiraConsultaItem.text = consulta?.medico?.nome ?: ""
                textViewMedicoConsultaTitulo.text = "Médico"
                textViewMedicoConsultaItem.text = consulta?.paciente?.nome ?: ""
                textViewPacienteConsultaTitulo.text = "Paciente"
                textViewPacienteConsultaItem.text = consulta?.pulseira?.pulseira ?: ""
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