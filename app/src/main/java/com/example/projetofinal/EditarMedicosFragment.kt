package com.example.projetofinal

import android.database.Cursor
import android.net.Uri
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
import com.example.projetofinal.databinding.FragmentEditarMedicoBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [EditarMedicosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarMedicosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentEditarMedicoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var medico: Medico? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarMedicoBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_edicao

        if (arguments != null) {
            medico = EditarMedicosFragmentArgs.fromBundle(requireArguments()).medico
            if (medico != null) {
                binding.editTextNome.setText(medico!!.nome)
                binding.editTextTelemovel.setText(medico!!.telemovel.toString())
                binding.editTextEmail.setText(medico!!.email)
                binding.editTextGenero.setText(medico!!.genero)
                binding.editTextCartaoCidadao.setText(medico!!.cartao_cidadao.toString())
            }
        }

        LoaderManager.getInstance(this).initLoader(ID_LOADER_ESPECIALIDADES, null, this)

        activity.atualizaTitulo(if (medico == null) R.string.inserir_medico_label else R.string.alterar_medico_label)
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
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
        findNavController().navigate(R.id.action_EditarMedicosFragment_to_ListaMedicosFragment)
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

        val genero = binding.editTextGenero.text.toString()
        if (genero.isBlank()) {
            binding.editTextGenero.error = getString(R.string.genero_obrigatorio)
            binding.editTextGenero.requestFocus()
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
        if (medico == null) {
            insereMedico(nome, telemovel, email, genero, cartaocidadao, idEspecialidade)
        } else {
            alteraMedico(nome, telemovel, email, genero, cartaocidadao, idEspecialidade)
        }
    }


    private fun alteraMedico(nome: String, telemovel: String, email: String, genero: String,cartaocidadao: String,idEspecialidade: Long) {
        val enderecoMedico = Uri.withAppendedPath(ContentProviderConsultas.ENDERECO_MEDICOS, "${medico!!.id}")

        val medico = Medico(
            nome,
            telemovel,
            email,
            genero,
            cartaocidadao,
            Especialidade(
                "",
                idEspecialidade
            )
        )

        val registosAlterados = requireActivity().contentResolver.update(
            enderecoMedico,
            medico.toContentValues(),
            null,
            null
        )

        if (registosAlterados == 1) {
            Toast.makeText(requireContext(), R.string.medico_alterado_sucesso, Toast.LENGTH_LONG).show()
            voltaListaMedicos()
        } else {
            Snackbar.make(
                binding.editTextNome,
                R.string.erro_guardar_medico,
                Snackbar.LENGTH_INDEFINITE
            ).show()
        }
    }

    private fun insereMedico(nome: String, telemovel: String, email: String, genero: String,cartaocidadao: String, idEspecialidade: Long){
        val medico = Medico(
            nome,
            telemovel,
            email,
            genero,
            cartaocidadao,
            Especialidade(
                "",
                idEspecialidade
            )
        )

        val endereco = requireActivity().contentResolver.insert(
            ContentProviderConsultas.ENDERECO_MEDICOS,
            medico.toContentValues()
        )

        if(endereco != null){
            Toast.makeText(requireContext(), R.string.medico_inserido_sucesso, Toast.LENGTH_LONG).show()
            voltaListaMedicos()
        } else {
            Snackbar.make(
                binding.editTextNome,
                R.string.erro_guardar_medico,
                Snackbar.LENGTH_INDEFINITE
            ).show()
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
            TabelaBDEspecialidades.CAMPO_ESPECIALIDADE
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

        mostraEspecialidadeSelecionadaSpinner()
    }

    private fun mostraEspecialidadeSelecionadaSpinner() {
        if (medico == null) return

        val idEspecialidade = medico!!.especialidade.id

        val ultimaEspecialidade = binding.spinnerEspecialidades.count - 1
        for (i in 0..ultimaEspecialidade) {
            if (idEspecialidade == binding.spinnerEspecialidades.getItemIdAtPosition(i)) {
                binding.spinnerEspecialidades.setSelection(i)
                return
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        binding.spinnerEspecialidades.adapter = null
    }
}