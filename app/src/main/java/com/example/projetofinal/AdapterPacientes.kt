package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterPacientes: RecyclerView.Adapter<AdapterPacientes.ViewHolderPacientes>() {

    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    class ViewHolderPacientes(itemPaciente: View) : RecyclerView.ViewHolder(itemPaciente) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPacientes {
        TODO("Not yet implemented")
    }


    override fun onBindViewHolder(holder: ViewHolderPacientes, position: Int) {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}