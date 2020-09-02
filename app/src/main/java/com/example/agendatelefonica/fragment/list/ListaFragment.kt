package com.example.agendatelefonica.fragment.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendatelefonica.R
import com.example.agendatelefonica.vistamodelo.UserModel
import kotlinx.android.synthetic.main.fragment_lista.view.*


class ListaFragment : Fragment() {

    private lateinit var mUserModel: UserModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_lista, container, false)

        val adapta = ListAdapta ()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapta
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserModel = ViewModelProvider(this).get(UserModel::class.java)
        mUserModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapta.setData(user)


        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listaFragment_to_agregarFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
           inflater.inflate(R.menu.borrar_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuBorrar) {
            borrarTodosUser()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun borrarTodosUser() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si"){_, _ ->
            mUserModel.borrarTodosUser()
            Toast.makeText(
                    requireContext(),
                    "Borrado Para Siempre",
                    Toast.LENGTH_SHORT).show()

        }

        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Borrar Por Siempre")
        builder.setMessage("Deceas Borrar Todos Los Contactos?")
        builder.create().show()
    }

    }
