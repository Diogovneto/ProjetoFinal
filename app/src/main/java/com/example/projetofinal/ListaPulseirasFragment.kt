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
import com.example.projetofinal.databinding.FragmentListaPulseirasBinding

class ListaPulseirasFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    var pulseiraSelecionada: Pulseira? = null
        get() = field
        set(value) {
            if (value != field) {
                field = value
                (requireActivity() as MainActivity).atualizaOpcoesLista(field != null)
            }
        }

    private var _binding: FragmentListaPulseirasBinding? = null

    private var adapterPulseira: AdapterPulseira? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaPulseirasBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_PULSEIRAS, null, this)

        adapterPulseira = AdapterPulseira(this)
        binding.recyclerViewLivros.adapter = adapterPulseira
        binding.recyclerViewLivros.layoutManager = LinearLayoutManager(requireContext())

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_pulseiras
    }

    fun processaOpcaoMenuPulseira(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaPulseirasFragmentDirections.actionListaPulseirasFragmentToEditarPulseiraFragment()
                findNavController().navigate(acao)
                return true
            }
            R.id.action_alterar -> {
                val acao = ListaPulseirasFragmentDirections.actionListaPulseirasFragmentToEditarPulseiraFragment(pulseiraSelecionada)
                findNavController().navigate(acao)
                return true
            }
            R.id.action_eliminar -> {
                val acao = ListaPulseirasFragmentDirections.actionListaPulseirasFragmentToEliminarPulseiraFragment(pulseiraSelecionada!!)
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
            ContentProviderConsultas.ENDERECO_PULSEIRAS,
            TabelaBDPulseiras.TODAS_COLUNAS,
            null,
            null,
            TabelaBDPulseiras.CAMPO_PULSEIRA
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterPulseira!!.cursor = data
    }


    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterPulseira!!.cursor = null
    }

    companion object {
        const val ID_LOADER_PULSEIRAS = 0
    }
}