package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
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

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMedicos {
        val itemMedico = fragment.layoutInflater.inflate(R.layout.item_medico, parent, false)
        return ViewHolderMedicos(itemMedico)
    }


    override fun onBindViewHolder(holder: ViewHolderMedicos, position: Int) {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}