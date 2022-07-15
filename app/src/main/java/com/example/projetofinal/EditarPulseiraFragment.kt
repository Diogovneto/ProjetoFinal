package com.example.projetofinal

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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditarPulseiraFragment : Fragment() {
    private var _binding: FragmentEditarPulseiraBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


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

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao_pulseira
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


        val pulseira = Pulseira(pulseira_consulta)

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderConsultas.ENDERECO_PULSEIRAS,
            pulseira.toContentValues()
        )

        if (endereco != null) {
            Toast.makeText(requireContext(), R.string.pulseira_inserida_sucesso, Toast.LENGTH_LONG).show()
            voltaListaPulseiras()
        } else {
            Snackbar.make(binding.editTextPulseira, R.string.erro_inserir_pulseira, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InserirLivroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditarPulseiraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}