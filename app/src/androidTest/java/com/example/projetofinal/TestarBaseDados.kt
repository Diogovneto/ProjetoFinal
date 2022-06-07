package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BaseDadosTest {
    fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BDOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereMedico(db: SQLiteDatabase, medico: Medico) {
        medico.id = TabelaBDMedicos(db).insert(medico.toContentValues())
        Assert.assertNotEquals(-1, medico.id)
    }

    private fun inserePaciente(db: SQLiteDatabase, paciente: Paciente) {
        paciente.id = TabelaBDPacientes(db).insert(paciente.toContentValues())
        Assert.assertNotEquals(-1, paciente.id)
    }

    @Before
    fun apagaBaseDados() {
        appContext().deleteDatabase(BDOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = BDOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueInserirMedico() {
        val db = getWritableDatabase()

        insereMedico(db, Medico(
            "Teste 1",
        "Consulta",
        934563467,
        "teste@gmail.com",
        "Masculino",
        14537834))

        insereMedico(db, Medico(
            "Teste 2",
            "Consulta",
            928754328,
            "teste2@gmail.com",
            "Feminino",
            13764567))

        db.close()
    }

    @Test
    fun consegueInserirPaciente() {
        val db = getWritableDatabase()

        inserePaciente(db, Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua Teste n285",
            "3750-598",
            938059434,
            "dvnetoubz@gmail.com",
            14537834,
            238316050))

        db.close()
    }

    @Test
    fun consegueInserirConsulta() {
        val db = getWritableDatabase()

        val medico = Medico(
                "Teste 1",
            "Consulta",
            928754328,
            "teste@gmail.com",
            "Masculino",
            14537834)

        insereMedico(db, medico)

        val paciente = Paciente(
            "Diogo Neto123",
            "27/07/2001",
            "Masculino",
            "Rua Teste n285",
            "3750-598",
            938059434,
            "dvnetoubz@gmail.com",
            14537834,
            238316050)

        inserePaciente(db, paciente)


        val Consulta = Consulta(
            "07/06/2022",
            "Infantil",
            "Amarela",
            "Garganta Inflamada",
            15,
            medico.id,
            paciente.id)

        Consulta.id = TabelaBDConsultas(db).insert(Consulta.toContentValues())

        Assert.assertNotEquals(-3, Consulta.id)

        db.close()
    }
}