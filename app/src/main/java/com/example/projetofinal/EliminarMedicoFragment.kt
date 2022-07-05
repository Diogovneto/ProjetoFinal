package com.example.projetofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projetofinal.databinding.FragmentEliminarMedicoBinding

class EliminarMedicoFragment : Fragment() {
    private var _binding: FragmentEliminarMedicoBinding? = null

    private val binding get() = _binding!!

    private lateinit var medico: Medico

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarMedicoBinding.inflate(inflater, container, false)
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
            activity.idMenuAtual = R.menu.menu_eliminar

            medico = EliminarMedicoFragmentArgs.fromBundle(requireArguments()).medico

            binding.textViewNome.text = medico.nome
            binding.textViewTelemovel.text = medico.telemovel.toString()
            binding.textViewEmail.text = medico.email
            binding.textViewSexo.text = medico.sexo
            binding.textViewCartaoCidadao.text = medico.cartao_cidadao.toString()
            binding.textViewEspecialidade.text = medico.especialidade.especialidade
        }

        fun processaOpcaoMenu(item: MenuItem): Boolean {
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