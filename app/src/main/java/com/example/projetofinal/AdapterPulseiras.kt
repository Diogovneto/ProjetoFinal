package com.example.projetofinal

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        val textViewPulseira = itemPulseira.findViewById<TextView>(R.id.textViewPulseira)

        var pulseira: Pulseira? = null
            get() = field
            set(value) {
                field = value

                textViewPulseira.text = pulseira?.pulseira ?: ""
            }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPulseira {
        val itemLivro = fragment.layoutInflater.inflate(R.layout.item_pulseira, parent, false)
        return ViewHolderPulseira(itemLivro)
    }


    override fun onBindViewHolder(holder: ViewHolderPulseira, position: Int) {
        cursor!!.moveToPosition(position)
        holder.pulseira = Pulseira.fromCursor(cursor!!)
    }


    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}