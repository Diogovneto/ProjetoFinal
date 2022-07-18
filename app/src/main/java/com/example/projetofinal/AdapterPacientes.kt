package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterPacientes(val fragment: ListaPacientesFragment): RecyclerView.Adapter<AdapterPacientes.ViewHolderPacientes>() {

    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    inner class ViewHolderPacientes(itemPaciente: View) : RecyclerView.ViewHolder(itemPaciente), View.OnClickListener {
        val textViewNome = itemPaciente.findViewById<TextView>(R.id.textViewNome)
        val textViewDataNascimento = itemPaciente.findViewById<TextView>(R.id.textViewDataNascimento)
        val textViewSexo = itemPaciente.findViewById<TextView>(R.id.textViewSexo)
        val textViewMorada = itemPaciente.findViewById<TextView>(R.id.textViewMorada)
        val textViewCodigoPostal = itemPaciente.findViewById<TextView>(R.id.textViewCodigoPostal)
        val textViewTelemovel = itemPaciente.findViewById<TextView>(R.id.textViewTelemovel)
        val textViewEmail = itemPaciente.findViewById<TextView>(R.id.textViewEmail)
        val textViewCartaoCidadao = itemPaciente.findViewById<TextView>(R.id.textViewCartaoCidadaoPaciente)
        val textViewContribuinte = itemPaciente.findViewById<TextView>(R.id.textViewContribuinte)

        init {
            itemPaciente.setOnClickListener(this)
        }

        var paciente: Paciente? = null
            get() = field
            set(value) {
                field = value

                textViewNome.text = paciente?.nome ?: ""
                textViewDataNascimento.text = paciente?.data_nascimento.toString()
                textViewSexo.text = paciente?.sexo ?: ""
                textViewMorada.text = paciente?.morada ?: ""
                textViewCodigoPostal.text = paciente?.codigo_postal ?: ""
                textViewTelemovel.text = paciente?.telemovel ?: ""
                textViewEmail.text = paciente?.email ?: ""
                textViewCartaoCidadao.text = paciente?.cartao_cidadao ?: ""
                textViewContribuinte.text = paciente?.contribuinte ?: ""
            }

        override fun onClick(v: View?) {
            seleccionado?.desseleciona()
            this.seleciona()
        }

        private fun seleciona() {
            seleccionado = this
            fragment.pacienteSelecionado = paciente
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPacientes {
        val itemPaciente = fragment.layoutInflater.inflate(R.layout.item_paciente, parent, false)
        return ViewHolderPacientes(itemPaciente)
    }


    override fun onBindViewHolder(holder: ViewHolderPacientes, position: Int) {
        cursor!!.moveToPosition(position)
        holder.paciente = Paciente.fromCursor(cursor!!)
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

    companion object {
        var seleccionado : ViewHolderPacientes? = null
    }
}