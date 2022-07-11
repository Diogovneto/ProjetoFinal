package com.example.projetofinal

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentEditarEspecialidadeBinding
import com.google.android.material.snackbar.Snackbar


class EditarEspecialidadeFragment : Fragment() {
    private var _binding: FragmentEditarEspecialidadeBinding? = null

    private val binding get() = _binding!!

    private var especialidade: Especialidade? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_especialidade, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao_especialidade

        if (arguments != null) {
            especialidade = EditarEspecialidadeFragmentArgs.fromBundle(requireArguments()).especialidade
            if (especialidade != null) {
                binding.editTextEspecialidade.setText(especialidade!!.especialidade)
            }
        }

        activity.atualizaTitulo(if (especialidade == null) R.string.editar_especialidade_label else R.string.alterar_especialidade_label)
    }

    fun processaOpcaoMenuEspecialidade(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaEspecialidades()
                true
            }
            else -> false
        }
    }

    private fun voltaListaEspecialidades() {
        findNavController().navigate(R.id.action_EditarEspecialidadeFragment_to_ListaEspecialidadeFragment)
    }

    private fun guardar() {
        val especialidade = binding.editTextEspecialidade.text.toString()
        if (especialidade.isBlank()) {
            binding.editTextEspecialidade.error = getString(R.string.especialidade_obrigatoria_especialidade)
            binding.editTextEspecialidade.requestFocus()
            return
        }

        if (especialidade == null) {
            insereEspecialidade(especialidade)
        } else {
            alteraEspecialidade(especialidade)
        }
    }

    private fun alteraEspecialidade(especialidade_nome: String) {
        val enderecoEspecialidade = Uri.withAppendedPath(ContentProviderConsultas.ENDERECO_ESPECIALIDADES, "${especialidade!!.id}")

        val especialidade = Especialidade(especialidade_nome)

        val registosAlterados = requireActivity().contentResolver.update(
            enderecoEspecialidade,
            especialidade.toContentValues(),
            null,
            null
        )

        if (registosAlterados == 1) {
            Toast.makeText(requireContext(), R.string.especialidade_alterada_sucesso, Toast.LENGTH_LONG).show()
            voltaListaEspecialidades()
        } else {
            Snackbar.make(
                binding.editTextEspecialidade,
                R.string.erro_guardar_especialidade,
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }

    private fun insereEspecialidade(especialidade: String) {
        val especialidade = Especialidade(especialidade)

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderConsultas.ENDERECO_ESPECIALIDADES,
            especialidade.toContentValues()
        )

        if (endereco != null) {
            Toast.makeText(requireContext(), R.string.especialidade_inserido_sucesso, Toast.LENGTH_LONG).show()
            voltaListaEspecialidades()
        } else {
            Snackbar.make(
                binding.editTextEspecialidade,
                R.string.erro_guardar_especialidade,
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }
    
}