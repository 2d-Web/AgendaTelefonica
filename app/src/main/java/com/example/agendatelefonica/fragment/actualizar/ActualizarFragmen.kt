package com.example.agendatelefonica.fragment.actualizar

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.agendatelefonica.R
import com.example.agendatelefonica.modelo.User
import com.example.agendatelefonica.vistamodelo.UserModel
import kotlinx.android.synthetic.main.fragment_actualizar.*
import kotlinx.android.synthetic.main.fragment_actualizar.view.*
import kotlinx.android.synthetic.main.fragment_agregar.*

class ActualizarFragmen : Fragment() {

private val args by navArgs<ActualizarFragmenArgs>()

    private lateinit var mUserViewModel: UserModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_actualizar, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserModel::class.java)

        view.NombreContactoaUpdate.setText(args.actualUser.nombre)
        view.DireccionContactoUpdate.setText(args.actualUser.direccion)
        view.CorreoContactoUpdate.setText(args.actualUser.correo)
        view.NumeroContactoUpdate.setText(args.actualUser.toString())

        view.BotonAgregarUpdate.setOnClickListener {

        }

        setHasOptionsMenu(true)

    return view

}
    private fun actualizarItem() {

        val nombreContacto = NombreContactoaUpdate.text.toString()
        val direccionContacto = DireccionContactoUpdate.text.toString()
        val correoContacto = CorreoContactoUpdate.text.toString()
        val numeroContacto = NumeroContactoUpdate.text.toString()

        if (inputCheck(nombreContacto, direccionContacto, correoContacto, nombreContacto)) {

            val actualizarUser = User(args.actualUser.id, nombreContacto, direccionContacto, correoContacto, nombreContacto)
            mUserViewModel.actualizarUser(actualizarUser)
            Toast.makeText(requireContext(), "Actaulizado Con Exito", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_agregarFragment_to_listaFragment)
        }else{

            Toast.makeText(requireContext(), "Ocurrio Un Error", Toast.LENGTH_SHORT).show()

        }
    }
    private fun  inputCheck(NombreContacto: String, DireccionContacto: String, CorreoContacto:String, NumeroContacto: String): Boolean{
        return !(TextUtils.isEmpty(NombreContacto) && TextUtils.isEmpty(DireccionContacto) && TextUtils.isEmpty(CorreoContacto) && NumeroContacto.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.borrar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menuBorrar){
            borrarUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun borrarUser() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si"){_, _ ->
            mUserViewModel.borrarUser(args.actualUser)
            Toast.makeText(
                    requireContext(),
                    "Borrado Con Exito: ${args.actualUser.nombre}",
                    Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_agregarFragment_to_listaFragment)

        }

        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Borrar ${args.actualUser.nombre}?")
        builder.setMessage("Deceas Borrar El Contacto ${args.actualUser.nombre}?")
        builder.create().show()
    }
 }
