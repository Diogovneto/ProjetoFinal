package com.example.projetofinal

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentEliminarEspecialidadeBinding
import com.google.android.material.snackbar.Snackbar

class EliminarEspecialidadeFragment : Fragment() {
    private var _binding: FragmentEliminarEspecialidadeBinding? = null

    private val binding get() = _binding!!

    private lateinit var especialidade: Especialidade

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarEspecialidadeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar_especialidade

        especialidade = EliminarEspecialidadeFragmentArgs.fromBundle(requireArguments()).especialidade

        binding.textViewEspecialidade.text = especialidade.especialidade

    }

    fun processaOpcaoMenuEspecialidade(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
                confirmaEliminaEspecialidade()
                true
            }
            R.id.action_cancelar -> {
                voltaListaEspecialidades()
                true
            }
            else -> false
        }
    }

    private fun confirmaEliminaEspecialidade() {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(R.string.titulo_dialogo_apagar_especialidade)
        alert.setMessage(R.string.confirma_apagar_especialidade)
        alert.setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialog, which ->  })
        alert.setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialog, which -> eliminaEspecialidade() })
        alert.show()
    }

    private fun eliminaEspecialidade() {
        val enderecoEspecialidadeApagar = Uri.withAppendedPath(ContentProviderConsultas.ENDERECO_ESPECIALIDADES, "${especialidade.id}")

        val registosEliminados = requireActivity().contentResolver.delete(enderecoEspecialidadeApagar, null, null)

        if (registosEliminados == 1) {
            Toast.makeText(requireContext(), R.string.especialidade_eliminada_sucesso, Toast.LENGTH_LONG).show()
            voltaListaEspecialidades()
        } else {
            Snackbar.make(binding.textViewEspecialidade, R.string.erro_eliminar_especialidade, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    private fun voltaListaEspecialidades() {
        val acao = EliminarEspecialidadeFragmentDirections.actionEliminarEspecialidadeFragmentToListaEspecialidadeFragment()
        findNavController().navigate(acao)
    }
}