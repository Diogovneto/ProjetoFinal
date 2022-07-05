package com.example.projetofinal

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InserirPacienteFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserir_paciente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_PULSEIRAS, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        //activity.idMenuAtual = R.menu.
    }

    fun processaOpcaoMenuPaciente(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_inserir -> {
                findNavController().navigate(R.id.action_ListaPacienteFragment_to_InserirPacienteFragment)
                return true
            }
            R.id.action_alterar -> true
            R.id.action_eliminar -> true
            else -> false
        }
    }

    companion object {
        const val ID_LOADER_PULSEIRAS = 0
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderConsultas.ENDERECO_PULSEIRAS,
            TabelaBDPacientes.TODAS_COLUNAS,
            null,
            null,
            TabelaBDPulseiras.CAMPO_PULSEIRA
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        TODO("Not yet implemented")
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }
}