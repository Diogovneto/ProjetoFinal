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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetofinal.databinding.FragmentListaPacientesBinding

class ListaPacientesFragment: Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaPacientesBinding? = null
    private var adapterPacientes: AdapterPacientes? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaPacientesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_PACIENTES, null, this)
        adapterPacientes = AdapterPacientes(this)
        binding.recyclerViewPacientes.adapter = adapterPacientes
        binding.recyclerViewPacientes.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_pacientes
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderConsultas.ENDERECO_PACIENTES,
            TabelaBDPacientes.TODAS_COLUNAS,
            null,
            null,
            TabelaBDPacientes.CAMPO_NOME
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterPacientes!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterPacientes!!.cursor = null
    }

    companion object {
        const val ID_LOADER_PACIENTES = 0
    }
}