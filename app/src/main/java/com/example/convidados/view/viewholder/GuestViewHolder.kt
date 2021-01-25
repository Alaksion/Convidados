package com.example.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.model.GuestModel
import com.example.convidados.view.listener.Guestlistener
import kotlinx.android.synthetic.main.row_guest.view.*
import kotlin.coroutines.coroutineContext

class GuestViewHolder(itemView : View, private val listener: Guestlistener) : RecyclerView.ViewHolder(itemView){

    fun bind(guest : GuestModel){
        val guestNameTextView = itemView.findViewById<TextView>(R.id.tv_guest_name)
        guestNameTextView.text = guest.name

        guestNameTextView.setOnClickListener {
            listener.onClick(guest.id)
        }

        guestNameTextView.setOnLongClickListener{
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja deletar convidado?")
                .setPositiveButton("Remover") { dialog , wich ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton("Cancelar", null)
                .show()
            true
        }
    }

}