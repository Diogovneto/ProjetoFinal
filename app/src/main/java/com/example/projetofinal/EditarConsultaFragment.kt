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


class EditarConsultaFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

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
        return inflater.inflate(R.layout.fragment_editar_consulta, container, false)
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
        when (id) {
            ID_LOADER_PULSEIRAS -> {
                CursorLoader(
                    requireContext(),
                    ContentProviderConsultas.ENDERECO_PULSEIRAS,
                    TabelaBDConsultas.TODAS_COLUNAS,
                    null,
                    null,
                    TabelaBDPulseiras.CAMPO_PULSEIRA
                )
            }
            ID_LOADER_MEDICOS -> {
                CursorLoader(
                    requireContext(),
                    ContentProviderConsultas.ENDERECO_MEDICOS,
                    TabelaBDConsultas.TODAS_COLUNAS,
                    null,
                    null,
                    TabelaBDMedicos.CAMPO_NOME
                )
            }
            ID_LOADER_PACIENTES -> {
                CursorLoader(
                    requireContext(),
                    ContentProviderConsultas.ENDERECO_PACIENTES,
                    TabelaBDConsultas.TODAS_COLUNAS,
                    null,
                    null,
                    TabelaBDPacientes.CAMPO_NOME
                )
            }
            else -> CursorLoader(requireContext(),
                ContentProviderConsultas.ENDERECO_ESPECIALIDADES,
                TabelaBDConsultas.TODAS_COLUNAS,
                null,
                null,
                null)
        }



    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        TODO("Not yet implemented")
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }

}