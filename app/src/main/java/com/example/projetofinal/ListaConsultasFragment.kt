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
import com.example.projetofinal.databinding.FragmentListaConsultasBinding

class ListaConsultasFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaConsultasBinding? = null

    private val binding get() = _binding!!

    private var adapterConsultas: AdapterConsultas? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaConsultasBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_CONSULTAS, null, this)

        adapterConsultas = AdapterConsultas(this)
        binding.recyclerViewLivros.adapter = adapterConsultas
        binding.recyclerViewLivros.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_consultas
    }

    fun processaOpcaoMenuConsultas(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_inserir -> {
                findNavController().navigate(R.id.action_ListaConsultasFragment_to_EditarConsultaFragment)
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

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param id The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderConsultas.ENDERECO_CONSULTAS,
            TabelaBDConsultas.TODAS_COLUNAS,
            null,
            null,
            TabelaBDConsultas.CAMPO_ID
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterConsultas!!.cursor = data
    }


    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterConsultas!!.cursor = null
    }

    companion object {
        const val ID_LOADER_CONSULTAS = 0
    }

}