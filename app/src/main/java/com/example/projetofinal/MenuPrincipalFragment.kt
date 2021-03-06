package com.example.projetofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetofinal.databinding.FragmentMenuPrincipalBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuPrincipalFragment : Fragment() {

    private var _binding: FragmentMenuPrincipalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuPrincipalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonMedicos.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_ListaMedicosFragment)
        }

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_main

        binding.buttonPacientes.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_ListaPacientesFragment)
        }

        binding.buttonEspecialidades.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_ListaEspecialidadesFragment)
        }

        binding.buttonPulseiras.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_ListaPulseirasFragment)
        }

        binding.buttonConsultas.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_ListaConsultasFragment)
        }

    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> false
        }
    }

    fun processaOpcaoMenuPaciente(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> false
        }
    }

    fun processaOpcaoMenuEspecialidade(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}