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

    class ViewHolderMedicos(itemMedico: View) : RecyclerView.ViewHolder(itemMedico){
        val textViewNome = itemMedico.findViewById<TextView>(R.id.textViewNome)
        val textViewTelemovel = itemMedico.findViewById<TextView>(R.id.textViewTelemovel)
        val textViewEmail = itemMedico.findViewById<TextView>(R.id.textViewEmail)
        val textViewSexo = itemMedico.findViewById<TextView>(R.id.textViewSexo)
        val textViewCartaoCidadao = itemMedico.findViewById<TextView>(R.id.textViewCartaoCidadao)
        val textViewEspecialidade = itemMedico.findViewById<TextView>(R.id.textViewEspecialidade)

        var medico: Medico? = null
            get() = field
            set(value) {
                field = value

                textViewNome.text = medico?.nome ?: ""
                textViewTelemovel.text = (medico?.telemovel ?: "") as CharSequence?
                textViewEmail.text = medico?.email ?: ""
                textViewSexo.text = medico?.sexo ?: ""
                textViewCartaoCidadao.text = (medico?.cartao_cidadao ?: "") as CharSequence?
                textViewEspecialidade.text = medico?.especialidade?.especialidade ?: ""

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
}