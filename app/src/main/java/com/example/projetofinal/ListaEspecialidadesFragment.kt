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
import com.example.projetofinal.databinding.FragmentListaEspecialidadesBinding

class ListaEspecialidadesFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    var EspecialidadeSelecionada: Especialidade? = null
        get() = field
        set(value) {
            field = value
            (requireActivity() as MainActivity).atualizaOpcoesLista(field != null)
        }

    private var _binding: FragmentListaEspecialidadesBinding? = null
    private var adapterEspecialidades: AdapterEspecialidades? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaEspecialidadesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_ESPECIALIDADES, null, this)

        adapterEspecialidades = AdapterEspecialidades(this)
        binding.recyclerViewEspecialidades.adapter = adapterEspecialidades
        binding.recyclerViewEspecialidades.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista_especialidades
    }

    fun processaOpcaoMenuEspecialidade(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaEspecialidadesFragmentDirections.actionListaEspecialidadeFragmentToEditarEspecialidadeFragment()
                findNavController().navigate(acao)
                return true
            }
            R.id.action_alterar -> {
                val acao = ListaEspecialidadesFragmentDirections.actionListaEspecialidadeFragmentToEditarEspecialidadeFragment(EspecialidadeSelecionada)
                findNavController().navigate(acao)
                return true
            }
            R.id.action_eliminar -> {
                val acao = ListaEspecialidadesFragmentDirections.actionListaEspecialidadeFragmentToEliminarEspecialidadeFragment(EspecialidadeSelecionada!!)
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
            ContentProviderConsultas.ENDERECO_ESPECIALIDADES,
            TabelaBDEspecialidades.TODAS_COLUNAS,
            null,
            null,
            TabelaBDEspecialidades.CAMPO_ESPECIALIDADE
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterEspecialidades!!.cursor = data
    }


    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterEspecialidades!!.cursor = null

        if (_binding == null) return
    }

    companion object {
        const val ID_LOADER_ESPECIALIDADES = 0
    }
}