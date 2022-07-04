package com.example.projetofinal

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentInserirMedicoBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [InserirMedicosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirMedicosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentInserirMedicoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirMedicoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_ESPECIALIDADES, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaMedicos()
                true
            }
            else -> false
        }
    }

    private fun voltaListaMedicos() {
        findNavController().navigate(R.id.action_InserirMedicosFragment_to_ListaMedicosFragment)
    }

    private fun guardar() {
        val nome = binding.editTextNome.text.toString()
        if (nome.isBlank()) {
            binding.editTextNome.error = getString(R.string.nome_obrigatorio)
            binding.editTextNome.requestFocus()
            return
        }

        val telemovel = binding.editTextTelemovel.text.toString()
        if (telemovel.isBlank()) {
            binding.editTextTelemovel.error = getString(R.string.telemovel_obrigatorio)
            binding.editTextTelemovel.requestFocus()
            return
        }

        val email = binding.editTextEmail.text.toString()
        if (email.isBlank()) {
            binding.editTextEmail.error = getString(R.string.email_obrigatorio)
            binding.editTextEmail.requestFocus()
        }

        val sexo = binding.editTextSexo.text.toString()
        if (sexo.isBlank()) {
            binding.editTextSexo.error = getString(R.string.sexo_obrigatorio)
            binding.editTextSexo.requestFocus()
        }

        val cartaocidadao = binding.editTextCartaoCidadao.text.toString()
        if (cartaocidadao.isBlank()) {
            binding.editTextCartaoCidadao.error = getString(R.string.cartao_cidadao_obrigatorio)
            binding.editTextCartaoCidadao.requestFocus()
        }

        val idEspecialidade = binding.spinnerEspecialidades.selectedItemId
        if (idEspecialidade == Spinner.INVALID_ROW_ID) {
            binding.textViewEspecialidade.error = getString(R.string.especialidade_obrigatoria)
            binding.spinnerEspecialidades.requestFocus()
            return
        }

        val medico = Medico(nome, telemovel, email, sexo, cartaocidadao, Especialidade("",idEspecialidade))

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderConsultas.ENDERECO_MEDICOS,
            medico.toContentValues()
        )

        if (endereco != null) {
            Toast.makeText(requireContext(), R.string.medico_inserido_sucesso, Toast.LENGTH_LONG).show()
            voltaListaMedicos()
        } else {
            Snackbar.make(binding.editTextNome, R.string.erro_inserir_medico, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    companion object {
        const val ID_LOADER_ESPECIALIDADES = 0
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderConsultas.ENDERECO_ESPECIALIDADES,
            TabelaBDEspecialidades.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDEspecialidades.CAMPO_ESPECIALIDADE}"
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        binding.spinnerEspecialidades.adapter = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDEspecialidades.CAMPO_ESPECIALIDADE),
            intArrayOf(android.R.id.text1),
            0
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        binding.spinnerEspecialidades.adapter = null
    }
}