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
import com.example.projetofinal.databinding.FragmentEditarPacienteBinding
import com.google.android.material.snackbar.Snackbar

class EditarPacienteFragment : Fragment() {
    private var _binding: FragmentEditarPacienteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var paciente: Paciente? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarPacienteBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_edicao_paciente

        if (arguments != null) {
            paciente = EditarPacienteFragmentArgs.fromBundle(requireArguments()).paciente
            if (paciente != null) {
                binding.editTextNomePac.setText(paciente!!.nome)
                binding.editTextDataNascimento.setText(paciente!!.data_nascimento)
                binding.editTextGeneroPac.setText(paciente!!.genero)
                binding.editTextMorada.setText(paciente!!.morada)
                binding.EditTextCodigoPostal.setText(paciente!!.codigo_postal)
                binding.editTextTelemovelPac.setText(paciente!!.telemovel)
                binding.editTextEmailPac.setText(paciente!!.email)
                binding.editTextCartaoCidadaoPac.setText(paciente!!.cartao_cidadao)
                binding.editTextContribuinte.setText(paciente!!.contribuinte)

            }
        }
        activity.atualizaTitulo(if (paciente == null) R.string.inserir_paciente_label else R.string.alterar_paciente_label)
    }

    fun processaOpcaoMenuPaciente(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaPacientes()
                true
            }
            else -> false
        }
    }

    private fun voltaListaPacientes() {
        findNavController().navigate(R.id.action_EditarPacienteFragment_to_ListaPacientesFragment)
    }

    private fun guardar() {
        val nome = binding.editTextNomePac.text.toString()
        if (nome.isBlank()) {
            binding.editTextNomePac.error = getString(R.string.nomeP_obrigatorio)
            binding.editTextNomePac.requestFocus()
            return
        }

        val datanascimento = binding.editTextDataNascimento.text.toString()
        if (datanascimento.isBlank()) {
            binding.editTextDataNascimento.error = getString(R.string.data_nascimento_obrigatoria)
            binding.editTextDataNascimento.requestFocus()
            return
        }
        val genero = binding.editTextGeneroPac.text.toString()
        if (genero.isBlank()) {
            binding.editTextGeneroPac.error = getString(R.string.genero_obrigatorio)
            binding.editTextGeneroPac.requestFocus()
            return
        }
        val morada = binding.editTextMorada.text.toString()
        if (morada.isBlank()) {
            binding.editTextMorada.error = getString(R.string.morada_obrigatoria)
            binding.editTextMorada.requestFocus()
            return
        }
        val codigopostal = binding.EditTextCodigoPostal.text.toString()
        if (codigopostal.isBlank()) {
            binding.EditTextCodigoPostal.error = getString(R.string.data_nascimento_obrigatoria)
            binding.EditTextCodigoPostal.requestFocus()
            return
        }
        val telemovel = binding.editTextTelemovelPac.text.toString()
        if (telemovel.isBlank()) {
            binding.editTextTelemovelPac.error = getString(R.string.telemovelP_obrigatorio)
            binding.editTextTelemovelPac.requestFocus()
            return
        }
        val email = binding.editTextEmailPac.text.toString()
        if (email.isBlank()) {
            binding.editTextEmailPac.error = getString(R.string.emailP_obrigatorio)
            binding.editTextEmailPac.requestFocus()
            return
        }
        val cartaocidadao = binding.editTextCartaoCidadaoPac.text.toString()
        if (cartaocidadao.isBlank()) {
            binding.editTextCartaoCidadaoPac.error = getString(R.string.cartao_cidadaoP_obrigatorio)
            binding.editTextCartaoCidadaoPac.requestFocus()
            return
        }
        val contribuinte = binding.editTextContribuinte.text.toString()
        if (contribuinte.isBlank()) {
            binding.editTextContribuinte.error = getString(R.string.contribuinte_obrigatorio)
            binding.editTextContribuinte.requestFocus()
            return
        }

        if (paciente == null) {
            inserePaciente(nome, datanascimento, genero, morada, codigopostal, telemovel, email, cartaocidadao, contribuinte)
        } else {
            alteraPaciente(nome, datanascimento, genero, morada, codigopostal, telemovel, email, cartaocidadao, contribuinte)
        }
    }

    private fun alteraPaciente(
        nome: String,
        data_nascimento: String,
        genero: String,
        morada: String,
        codigo_postal: String,
        telemovel: String,
        email: String,
        cartao_cidadao: String,
        contribuinte: String) {
        val enderecoPaciente =
            Uri.withAppendedPath(ContentProviderConsultas.ENDERECO_PACIENTES, "${paciente!!.id}")

        val paciente = Paciente(
            nome,
            data_nascimento,
            genero,
            morada,
            codigo_postal,
            telemovel,
            email,
            cartao_cidadao,
            contribuinte
        )

        val registosAlterados = requireActivity().contentResolver.update(
            enderecoPaciente,
            paciente.toContentValues(),
            null,
            null
        )

        if (registosAlterados == 1) {
            Toast.makeText(requireContext(), R.string.paciente_alterado_sucesso, Toast.LENGTH_LONG)
                .show()
            voltaListaPacientes()
        } else {
            Snackbar.make(
                binding.editTextDataNascimento,
                R.string.erro_guardar_paciente,
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }

    }



    private fun inserePaciente(
            nome: String,
            data_nascimento: String,
            genero: String,
            morada: String,
            codigo_postal: String,
            telemovel: String,
            email: String,
            cartao_cidadao: String,
            contribuinte: String) {

        val paciente = Paciente(nome, data_nascimento, genero, morada, codigo_postal, telemovel, email, cartao_cidadao, contribuinte)

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderConsultas.ENDERECO_PACIENTES,
            paciente.toContentValues()
        )

        if (endereco != null) {
            Toast.makeText(requireContext(), R.string.paciente_inserido_sucesso, Toast.LENGTH_LONG).show()
            voltaListaPacientes()
        } else {
            Snackbar.make(
                binding.editTextNomePac,
                R.string.erro_guardar_paciente,
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }


}