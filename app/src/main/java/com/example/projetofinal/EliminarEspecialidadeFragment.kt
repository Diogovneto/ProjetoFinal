package com.example.projetofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projetofinal.databinding.FragmentEliminarEspecialidadeBinding

class EliminarEspecialidadeFragment : Fragment() {
    private var _binding: FragmentEliminarEspecialidadeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
                true
            }
            R.id.action_cancelar -> {
                true
            }
            else -> false
        }
    }
}