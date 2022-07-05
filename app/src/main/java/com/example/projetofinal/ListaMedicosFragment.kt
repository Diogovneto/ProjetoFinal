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
import com.example.projetofinal.databinding.FragmentMedicosLabelBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListaMedicosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    var MedicoSelecionado: Medico? = null
        get() = field
        set(value) {
            field = value
            (requireActivity() as MainActivity).atualizaOpcoesLista(field != null)
        }

    private var _binding: FragmentMedicosLabelBinding? = null
    private var adapterMedicos: AdapterMedicos? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMedicosLabelBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_MEDICOS, null, this)

        adapterMedicos = AdapterMedicos(this)
        binding.recyclerViewMedicos.adapter = adapterMedicos
        binding.recyclerViewMedicos.layoutManager = LinearLayoutManager(requireContext())

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaMedicosFragmentDirections.actionListaMedicosFragmentToEditarMedicosFragment()
                findNavController().navigate(acao)
                return true
            }
            R.id.action_alterar -> {
                val acao = ListaMedicosFragmentDirections.actionListaMedicosFragmentToEditarMedicosFragment(MedicoSelecionado)
                findNavController().navigate(acao)
                return true
            }
            R.id.action_eliminar -> {
                val acao = ListaMedicosFragmentDirections.actionListaMedicoFragmentToEliminarMedicoFragment(MedicoSelecionado!!)
                findNavController().navigate(acao)
                true
            }
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
            ContentProviderConsultas.ENDERECO_MEDICOS,
            TabelaBDMedicos.TODAS_COLUNAS,
            null,
            null,
            TabelaBDMedicos.CAMPO_NOME
        )



    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterMedicos!!.cursor = data
    }


    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        adapterMedicos!!.cursor = null
    }

    companion object {
        const val ID_LOADER_MEDICOS = 0
    }

}