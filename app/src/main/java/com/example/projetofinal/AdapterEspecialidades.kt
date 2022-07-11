package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterEspecialidades(val fragment: ListaEspecialidadesFragment) : RecyclerView.Adapter<AdapterEspecialidades.ViewHolderEspecialidades>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }
    class ViewHolderEspecialidades(itemEspecialidade: View) : RecyclerView.ViewHolder(itemEspecialidade), View.OnClickListener {
        val textViewEspecialidade = itemEspecialidade.findViewById<TextView>(R.id.textViewEspecialidade)

        init {
            itemEspecialidade.setOnClickListener(this)
        }

        var especialidade: Especialidade? = null
            get() = field
            set(value) {
                field = value

                textViewEspecialidade.text = especialidade?.especialidade ?: ""

            }

        override fun onClick(v: View?) {
            seleccionado?.desseleciona()
            this.seleciona()
        }

        private fun seleciona() {
            seleccionado = this
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }

        companion object {
            var seleccionado : ViewHolderEspecialidades? = null
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEspecialidades {
        val itemEspecialidade = fragment.layoutInflater.inflate(R.layout.item_especialidade, parent, false)
        return ViewHolderEspecialidades(itemEspecialidade)
    }

    override fun onBindViewHolder(holder: ViewHolderEspecialidades, position: Int) {
        cursor!!.moveToPosition(position)
        holder.especialidade = Especialidade.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}