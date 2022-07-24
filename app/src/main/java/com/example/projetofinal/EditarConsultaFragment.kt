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
import com.example.projetofinal.databinding.FragmentEditarConsultaBinding


class EditarConsultaFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentEditarConsultaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarConsultaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_PULSEIRAS, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_MEDICOS, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_PACIENTES, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao_consulta
    }

    fun processaOpcaoMenuConsultas(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_guardar -> true
            R.id.action_cancelar -> {
                findNavController().navigate(R.id.action_EditarConsultaFragment_to_ListaConsultasFragment)
                true
            }
            else -> false
        }
    }

    companion object {
        const val ID_LOADER_PULSEIRAS = 0
        const val ID_LOADER_MEDICOS = 0
        const val ID_LOADER_PACIENTES = 0
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
        /*when (id) {
            ID_LOADER_PULSEIRAS -> {
                CursorLoader(
                    requireContext(),
                    ContentProviderConsultas.ENDERECO_PULSEIRAS,
                    TabelaBDPulseiras.TODAS_COLUNAS,
                    null,
                    null,
                    TabelaBDPulseiras.CAMPO_PULSEIRA
                )
            }
            ID_LOADER_MEDICOS -> {
                CursorLoader(
                    requireContext(),
                    ContentProviderConsultas.ENDERECO_MEDICOS,
                    TabelaBDMedicos.TODAS_COLUNAS,
                    null,
                    null,
                    TabelaBDMedicos.CAMPO_NOME
                )
            }
            ID_LOADER_PACIENTES -> {
                CursorLoader(
                    requireContext(),
                    ContentProviderConsultas.ENDERECO_PACIENTES,
                    TabelaBDPacientes.TODAS_COLUNAS,
                    null,
                    null,
                    TabelaBDPacientes.CAMPO_NOME
                )
            }
            else -> CursorLoader(requireContext(),
                ContentProviderConsultas.ENDERECO_ESPECIALIDADES,
                TabelaBDEspecialidades.TODAS_COLUNAS,
                null,
                null,
                null)
        }*/



    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        binding.spinnerPulseiraConsulta.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDPulseiras.CAMPO_PULSEIRA),
            intArrayOf(android.R.id.text1),
            0
        )

        binding.spinnerMedicoConsulta.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDMedicos.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )

        binding.spinnerPacienteConsulta.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDPacientes.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        binding.spinnerPulseiraConsulta.adapter = null
        binding.spinnerMedicoConsulta.adapter = null
        binding.spinnerPacienteConsulta.adapter = null
    }

}