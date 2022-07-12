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
import com.example.projetofinal.databinding.FragmentEliminarPacienteBinding
import com.google.android.material.snackbar.Snackbar

class EliminarPacienteFragment : Fragment() {
    private var _binding: FragmentEliminarPacienteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var paciente: Paciente

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarPacienteBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_eliminar_paciente

        paciente = EliminarPacienteFragmentArgs.fromBundle(requireArguments()).paciente

        binding.textViewNomePaciente.text = paciente.nome
        binding.textViewDataNascimentoPaciente.text = paciente.data_nascimento
        binding.textViewSexoPaciente.text = paciente.sexo
        binding.textViewMorada.text = paciente.morada
        binding.textViewCodigoPostal.text = paciente.codigo_postal
        binding.textViewTelemovelPaciente.text = paciente.telemovel
        binding.textViewEmailPaciente.text = paciente.email
        binding.textViewCartaoCidadaoPaciente.text = paciente.cartao_cidadao
        binding.textViewContribuinte.text = paciente.contribuinte
    }

    fun processaOpcaoMenuPaciente(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
                confirmaEliminaPaciente()
                true
            }
            R.id.action_cancelar -> {
                voltaListaPacientes()
                true
            }
            else -> false
        }
    }

    private fun confirmaEliminaPaciente() {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(R.string.titulo_dialogo_apagar_paciente)
        alert.setMessage(R.string.confirma_apagar_paciente)
        alert.setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialog, which ->  })
        alert.setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialog, which -> eliminaPaciente() })
        alert.show()
    }

    private fun eliminaPaciente() {
        val enderecoPacienteApagar = Uri.withAppendedPath(ContentProviderConsultas.ENDERECO_PACIENTES, "${paciente.id}")

        val registosEliminados = requireActivity().contentResolver.delete(enderecoPacienteApagar, null, null)

        if (registosEliminados == 1) {
            Toast.makeText(requireContext(), R.string.paciente_eliminado_sucesso, Toast.LENGTH_LONG).show()
            voltaListaPacientes()
        } else {
            Snackbar.make(binding.textViewNomePaciente, R.string.erro_eliminar_paciente, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    private fun voltaListaPacientes() {
        val acao = EliminarPacienteFragmentDirections.actionEliminarPacienteFragmentToListaPacientesFragment()
        findNavController().navigate(acao)
    }
}