package com.example.projetofinal

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentInserirPacienteBinding

class InserirPacienteFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentInserirPacienteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirPacienteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            TabelaBDPulseiras.TODAS_COLUNAS,
            null,
            null,
            TabelaBDPulseiras.CAMPO_PULSEIRA
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        binding.spinnerPulseira.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDPulseiras.CAMPO_PULSEIRA),
            intArrayOf(android.R.id.text1),
            0
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        binding.spinnerPulseira.adapter = null
    }
}