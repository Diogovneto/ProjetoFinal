package com.example.projetofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projetofinal.databinding.FragmentEliminarMedicoBinding

class EliminarMedicoFragment : Fragment() {
    private var _binding: FragmentEliminarMedicoBinding? = null

    private val binding get() = _binding!!

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
        }

        fun processaOpcaoMenu(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_eliminar -> {
                    true
                }
                R.id.action_cancelar -> {
                    true
                }
                else -> false
            }
        }
}