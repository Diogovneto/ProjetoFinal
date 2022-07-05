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
import com.example.projetofinal.databinding.FragmentEliminarMedicoBinding
import com.google.android.material.snackbar.Snackbar

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
                    confirmaEliminaMedico()
                    true
                }
                R.id.action_cancelar -> {
                    voltaListaMedicos()
                    true
                }
                else -> false
            }
        }

    private fun confirmaEliminaMedico() {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(R.string.titulo_dialogo_apagar_medico)
        alert.setMessage(R.string.confirma_apagar_medico)
        alert.setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialog, which ->  })
        alert.setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialog, which -> eliminaMedico() })
        alert.show()
    }

    private fun eliminaMedico() {
        val enderecoLivroApagar = Uri.withAppendedPath(ContentProviderConsultas.ENDERECO_MEDICOS, "${medico.id}")

        val registosEliminados = requireActivity().contentResolver.delete(enderecoLivroApagar, null, null)

        if (registosEliminados == 1) {
            Toast.makeText(requireContext(), R.string.medico_eliminado_sucesso, Toast.LENGTH_LONG).show()
            voltaListaMedicos()
        } else {
            Snackbar.make(binding.textViewNome, R.string.erro_eliminar_medico, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    private fun voltaListaMedicos() {
        val acao = EliminarMedicoFragmentDirections.actionEliminarLivroFragmentToListaLivrosFragment()
        findNavController().navigate(acao)
    }
}