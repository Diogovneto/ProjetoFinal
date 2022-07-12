package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterPulseira(val fragment: ListaPulseirasFragment) : RecyclerView.Adapter<AdapterPulseira.ViewHolderPulseira>() {

    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    class ViewHolderPulseira(itemPulseira: View) : RecyclerView.ViewHolder(itemPulseira) {
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPulseira {
        TODO("Not yet implemented")
    }


    override fun onBindViewHolder(holder: ViewHolderPulseira, position: Int) {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}