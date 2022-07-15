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
import com.example.projetofinal.databinding.FragmentEditarPulseiraBinding
import com.google.android.material.snackbar.Snackbar

class EditarPulseiraFragment : Fragment() {
    private var _binding: FragmentEditarPulseiraBinding? = null

    private val binding get() = _binding!!

    private var pulseira: Pulseira? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarPulseiraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao_pulseira

        if (arguments != null) {
            pulseira = EditarPulseiraFragmentArgs.fromBundle(requireArguments()).pulseira
            if (pulseira != null) {
                binding.editTextPulseira.setText(pulseira!!.pulseira)
            }
        }

    }

    fun processaOpcaoMenuPulseira(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaPulseiras()
                true
            }
            else -> false
        }
    }

    private fun voltaListaPulseiras() {
        findNavController().navigate(R.id.action_EditarPulseiraFragment_to_ListaPulseirasFragment)
    }

    private fun guardar() {
        val pulseira_consulta = binding.editTextPulseira.text.toString()
        if (pulseira_consulta.isBlank()) {
            binding.editTextPulseira.error = getString(R.string.pulseira_obrigatoria)
            binding.editTextPulseira.requestFocus()
            return
        }

        if (pulseira == null) {
            inserePulseira(pulseira_consulta)
        } else {
            alteraPulseira(pulseira_consulta)
        }
    }

    private fun alteraPulseira(consulta_pulseira: String) {
        val enderecoPulseira = Uri.withAppendedPath(ContentProviderConsultas.ENDERECO_PULSEIRAS, "${pulseira!!.id}")

        val pulseira = Pulseira(consulta_pulseira)

        val registosAlterados = requireActivity().contentResolver.update(
            enderecoPulseira,
            pulseira.toContentValues(),
            null,
            null
        )

        if (registosAlterados == 1) {
            Toast.makeText(requireContext(), R.string.pulseira_alterada_sucesso, Toast.LENGTH_LONG).show()
            voltaListaPulseiras()
        } else {
            Snackbar.make(
                binding.editTextPulseira,
                R.string.erro_guardar_pulseira,
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }

    private fun inserePulseira(pulseira: String) {
        val pulseira = Pulseira(pulseira)

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderConsultas.ENDERECO_PULSEIRAS,
            pulseira.toContentValues()
        )

        if (endereco != null) {
            Toast.makeText(requireContext(), R.string.pulseira_inserida_sucesso, Toast.LENGTH_LONG).show()
            voltaListaPulseiras()
        } else {
            Snackbar.make(
                binding.editTextPulseira,
                R.string.erro_guardar_especialidade,
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }

}