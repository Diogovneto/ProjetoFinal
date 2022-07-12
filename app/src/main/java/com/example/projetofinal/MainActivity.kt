package com.example.projetofinal

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.projetofinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    var fragment : Fragment? = null

    var idMenuAtual = R.menu.menu_main
        get() = field
        set(value) {
            if (value != field) {
                field = value
                invalidateOptionsMenu()
            }
        }

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(idMenuAtual, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        val opcaoProcessada : Boolean

        if (fragment is MenuPrincipalFragment) {
            opcaoProcessada = (fragment as MenuPrincipalFragment).processaOpcaoMenu(item)
        } else if (fragment is ListaMedicosFragment) {
            opcaoProcessada = (fragment as ListaMedicosFragment).processaOpcaoMenu(item)
        } else if (fragment is EditarMedicosFragment) {
            opcaoProcessada = (fragment as EditarMedicosFragment).processaOpcaoMenu(item)
        } else if (fragment is EliminarMedicoFragment) {
            opcaoProcessada = (fragment as EliminarMedicoFragment).processaOpcaoMenu(item)
        } else if (fragment is ListaEspecialidadesFragment) {
            opcaoProcessada = (fragment as ListaEspecialidadesFragment).processaOpcaoMenuEspecialidade(item)
        } else if (fragment is EditarEspecialidadeFragment) {
            opcaoProcessada = (fragment as EditarEspecialidadeFragment).processaOpcaoMenuEspecialidade(item)
        } else if (fragment is EliminarEspecialidadeFragment) {
            opcaoProcessada = (fragment as EliminarEspecialidadeFragment).processaOpcaoMenuEspecialidade(item)
        } else if (fragment is ListaPacientesFragment) {
            opcaoProcessada = (fragment as ListaPacientesFragment).processaOpcaoMenuPaciente(item)
        } else if (fragment is EditarPacienteFragment) {
            opcaoProcessada = (fragment as EditarPacienteFragment).processaOpcaoMenuPaciente(item)
        } else if (fragment is EliminarPacienteFragment) {
            opcaoProcessada = (fragment as EliminarPacienteFragment).processaOpcaoMenuPaciente(item)
        } else if (fragment is ListaPulseirasFragment) {
            opcaoProcessada = (fragment as ListaPulseirasFragment).processaOpcaoMenuPulseira(item)
        } else if (fragment is EditarPulseiraFragment) {
            opcaoProcessada = (fragment as EditarPulseiraFragment).processaOpcaoMenuPulseira(item)
        }else {
            opcaoProcessada = false
        }

        return if (opcaoProcessada) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun atualizaOpcoesLista(mostraAlterarEliminar: Boolean) {
        menu!!.findItem(R.id.action_alterar).setVisible(mostraAlterarEliminar)
        menu!!.findItem(R.id.action_eliminar).setVisible(mostraAlterarEliminar)
    }

    fun atualizaTitulo(id_string: Int) {
        binding.toolbar.setTitle(id_string)
    }
}