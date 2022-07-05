package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMedicos(val fragment: ListaMedicosFragment) : RecyclerView.Adapter<AdapterMedicos.ViewHolderMedicos>() {

    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    inner class ViewHolderMedicos(itemMedico: View) : RecyclerView.ViewHolder(itemMedico), View.OnClickListener {
        val textViewNome = itemMedico.findViewById<TextView>(R.id.textViewNome)
        val textViewTelemovel = itemMedico.findViewById<TextView>(R.id.textViewTelemovel)
        val textViewEmail = itemMedico.findViewById<TextView>(R.id.textViewEmail)
        val textViewSexo = itemMedico.findViewById<TextView>(R.id.textViewSexo)
        val textViewCartaoCidadao = itemMedico.findViewById<TextView>(R.id.textViewCartaoCidadao)
        val textViewEspecialidade = itemMedico.findViewById<TextView>(R.id.textViewEspecialidade)

        init {
            itemMedico.setOnClickListener(this)
        }

        var medico: Medico? = null
            get() = field
            set(value) {
                field = value

                textViewNome.text = medico?.nome ?: ""
                textViewTelemovel.text = medico?.telemovel.toString()
                textViewEmail.text = medico?.email ?: ""
                textViewSexo.text = medico?.sexo ?: ""
                textViewCartaoCidadao.text = medico?.cartao_cidadao.toString()
                textViewEspecialidade.text = medico?.especialidade?.especialidade ?: ""

            }

        override fun onClick(v: View?) {
            seleccionado?.desseleciona()
            this.seleciona()
        }

        private fun seleciona() {
            seleccionado = this
            fragment.MedicoSelecionado = medico
            itemView.setBackgroundResource(android.R.color.holo_orange_dark)
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMedicos {
        val itemMedico = fragment.layoutInflater.inflate(R.layout.item_medico, parent, false)
        return ViewHolderMedicos(itemMedico)
    }


    override fun onBindViewHolder(holder: ViewHolderMedicos, position: Int) {
        cursor!!.moveToPosition(position)
        holder.medico = Medico.fromCursor(cursor!!)
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

    companion object {
        var seleccionado : ViewHolderMedicos? = null
    }
}