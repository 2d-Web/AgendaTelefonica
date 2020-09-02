package com.example.agendatelefonica.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.agendatelefonica.R
import com.example.agendatelefonica.modelo.User
import kotlinx.android.synthetic.main.person_room.view.*

class ListAdapta : RecyclerView.Adapter<ListAdapta.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_room,parent, false))

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val  currentItem = userList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.ContactoNombre.text = currentItem.nombre
        holder.itemView.TelefonoContacto.text = currentItem.telefono.toString()

        holder.itemView.filaLayout.setOnClickListener{
          val action = ListaFragmentDirections.actionListaFragmentToActualizarFragmen(currentItem)
            holder.itemView.findNavController().navigate(action)

        }

    }

    fun setData(user: List<User>){
    notifyDataSetChanged()
    }

}