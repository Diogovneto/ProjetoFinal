package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
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
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderConsultas {
        val itemConsulta = fragment.layoutInflater.inflate(R.layout.item_consulta, parent, false)
        return ViewHolderConsultas(itemConsulta)
    }


    override fun onBindViewHolder(holder: ViewHolderConsultas, position: Int) {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}