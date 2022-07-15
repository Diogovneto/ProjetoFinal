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
import com.example.projetofinal.databinding.FragmentEliminarPulseiraBinding
import com.google.android.material.snackbar.Snackbar

class EliminarPulseiraFragment : Fragment() {
    private var _binding: FragmentEliminarPulseiraBinding? = null
    private val binding get() = _binding!!

    private lateinit var pulseira: Pulseira

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarPulseiraBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_eliminar_pulseira

        pulseira = EliminarPulseiraFragmentArgs.fromBundle(requireArguments()).pulseira

        binding.textViewPulseira.text = pulseira.pulseira
    }

    fun processaOpcaoMenuPulseira(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_eliminar -> {
                confirmaEliminaPulseira()
                true
            }
            R.id.action_cancelar -> {
                voltaListaPulseiras()
                true
            }
            else -> false
        }
    }

    private fun confirmaEliminaPulseira() {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(R.string.titulo_dialogo_apagar_pulseira)
        alert.setMessage(R.string.confirma_apagar_pulseira)
        alert.setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialog, which ->  })
        alert.setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialog, which -> eliminaPulseira() })
        alert.show()
    }

    private fun eliminaPulseira() {
        val enderecoPulseiraApagar = Uri.withAppendedPath(ContentProviderConsultas.ENDERECO_PULSEIRAS, "${pulseira.id}")

        val registosEliminados = requireActivity().contentResolver.delete(enderecoPulseiraApagar, null, null)

        if (registosEliminados == 1) {
            Toast.makeText(requireContext(), R.string.pulseira_eliminada_sucesso, Toast.LENGTH_LONG).show()
            voltaListaPulseiras()
        } else {
            Snackbar.make(binding.textViewPulseira, R.string.erro_eliminar_pulseira, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

    private fun voltaListaPulseiras() {
        val acao = EliminarPulseiraFragmentDirections.actionEliminarLivroFragmentToListaLivrosFragment()
        findNavController().navigate(acao)
    }

}