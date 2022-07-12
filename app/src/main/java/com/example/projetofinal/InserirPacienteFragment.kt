package com.example.projetofinal

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentInserirPacienteBinding
import com.google.android.material.snackbar.Snackbar

class InserirPacienteFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentInserirPacienteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirPacienteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_PULSEIRAS, null, this)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao_paciente
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
        findNavController().navigate(R.id.action_InserirPacienteFragment_to_ListaPacientesFragment)
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
        val sexo = binding.editTextSexoPac.text.toString()
        if (sexo.isBlank()) {
            binding.editTextSexoPac.error = getString(R.string.sexo_obrigatorio)
            binding.editTextSexoPac.requestFocus()
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


        val paciente = Paciente(
            nome,
            datanascimento,
            sexo,
            morada,
            codigopostal,
            telemovel,
            email,
            cartaocidadao,
            contribuinte,

        )

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderConsultas.ENDERECO_PACIENTES,
            paciente.toContentValues()
        )

        if (endereco != null) {
            Toast.makeText(requireContext(), R.string.paciente_inserido_sucesso, Toast.LENGTH_LONG).show()
            voltaListaPacientes()
        } else {
            Snackbar.make(binding.editTextNomePac, R.string.erro_inserir_paciente, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    companion object {
        const val ID_LOADER_PULSEIRAS = 0
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderConsultas.ENDERECO_PULSEIRAS,
            TabelaBDPulseiras.TODAS_COLUNAS,
            null,
            null,
            TabelaBDPulseiras.CAMPO_PULSEIRA
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {

    }
}