package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
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
    class ViewHolderEspecialidades(itemEspecialidade: View) : RecyclerView.ViewHolder(itemEspecialidade) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEspecialidades {
        val itemEspecialidade = fragment.layoutInflater.inflate(R.layout.item_especialidade, parent, false)
        return ViewHolderEspecialidades(itemEspecialidade)
    }

    override fun onBindViewHolder(holder: ViewHolderEspecialidades, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}