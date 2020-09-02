package com.example.agendatelefonica.fragment.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.agendatelefonica.R
import com.example.agendatelefonica.modelo.User
import com.example.agendatelefonica.vistamodelo.UserModel
import kotlinx.android.synthetic.main.fragment_agregar.*
import kotlinx.android.synthetic.main.fragment_agregar.view.*


class AgregarFragment : Fragment() {

    private lateinit var mUserViewModel: UserModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_agregar, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserModel::class.java)

        view.BotonAgregar.setOnClickListener {
            insertDataToDatabase()
        }

return view

    }

    private fun insertDataToDatabase() {

        val NombreContacto = NombreContacto.text.toString()
        val NumeroContacto = NumeroContacto.text.toString()
        val DireccionConatcto = DireccionContacto.text.toString()
        val CorreoContacto = CorreoContacto.text.toString()

        if (inputCheck(NombreContacto, NumeroContacto, DireccionConatcto, CorreoContacto)){

            val user = User(0, NombreContacto,DireccionConatcto,CorreoContacto, NumeroContacto.toString())

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Contacto Agregado Con Exito", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_agregarFragment_to_listaFragment)
        }else{

            Toast.makeText(requireContext(), "Ocurrio Un Error Al Agregar El Contacto", Toast.LENGTH_LONG).show()

        }

    }

    private fun  inputCheck(NombreContacto: String, DireccionContacto: String, CorreoContacto:String, NumeroContacto: String): Boolean{
        return !(TextUtils.isEmpty(NombreContacto) && TextUtils.isEmpty(DireccionContacto) && TextUtils.isEmpty(CorreoContacto) && NumeroContacto.isEmpty())
    }
}
