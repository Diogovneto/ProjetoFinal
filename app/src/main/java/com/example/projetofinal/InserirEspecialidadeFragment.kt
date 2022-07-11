package com.example.projetofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentInserirEspecialidadeBinding
import com.google.android.material.snackbar.Snackbar


class InserirEspecialidadeFragment : Fragment() {
    private var _binding: FragmentInserirEspecialidadeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserir_especialidade, container, false)
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
        findNavController().navigate(R.id.action_InserirEspecialidadeFragment_to_ListaEspecialidadeFragment)
    }

    private fun guardar() {
        val especialidade = binding.editTextEspecialidade.text.toString()
        if (especialidade.isBlank()) {
            binding.editTextEspecialidade.error = getString(R.string.especialidade_obrigatoria_especialidade)
            binding.editTextEspecialidade.requestFocus()
            return
        }

        val especialidade_insere = Especialidade(
            especialidade// O nome da categoria não interessa porque o que é guardado é a chave estrangeira
        )

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderConsultas.ENDERECO_ESPECIALIDADES,
            especialidade_insere.toContentValues()
        )

        if (endereco != null) {
            Toast.makeText(requireContext(), R.string.especialidade_inserido_sucesso, Toast.LENGTH_LONG).show()
            voltaListaEspecialidades()
        } else {
            Snackbar.make(binding.editTextEspecialidade, R.string.erro_inserir_especialidade, Snackbar.LENGTH_INDEFINITE).show()
        }
    }
    
}