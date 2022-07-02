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
import com.example.projetofinal.databinding.FragmentInserirMedicoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InserirMedicosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirMedicosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentInserirMedicoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirMedicoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_ESPECIALIDADES, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        //activity.idMenuAtual = R.menu.
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_guardar -> true
            R.id.action_cancelar -> {
                findNavController().navigate(R.id.action_InserirMedicosFragment_to_ListaMedicosFragment)
                true
            }
            else -> false
        }
    }

    companion object {
        const val ID_LOADER_ESPECIALIDADES = 0
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderConsultas.ENDERECO_ESPECIALIDADES,
            TabelaBDEspecialidades.TODAS_COLUNAS,
            null,
            null,
            TabelaBDEspecialidades.CAMPO_ESPECIALIDADE
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        binding.spinnerEspecialidades.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDEspecialidades.CAMPO_ESPECIALIDADE),
            intArrayOf(android.R.id.text1),
            0
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        binding.spinnerEspecialidades.adapter = null
    }
}