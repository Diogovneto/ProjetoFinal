package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Assert.assertEquals
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

    private fun insereConsulta(db: SQLiteDatabase, consulta: Consulta) {
        consulta.id = TabelaBDConsultas(db).insert(consulta.toContentValues())
        Assert.assertNotEquals(-1, consulta.id)
    }

    private fun inserePulseira(db: SQLiteDatabase, pulseira: Pulseira){
        pulseira.id = TabelaBDPulseiras(db).insert(pulseira.toContentValues())
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

        insereMedico(
            db, Medico(
                "Teste 1",
                "Consulta",
                934563467,
                "teste@gmail.com",
                "Masculino",
                14537834
            )
        )
    }

        @Test
        fun consegueInserirPaciente() {
            val db = getWritableDatabase()

            val pulseira = Pulseira("Verde")

            inserePaciente(
                db, Paciente(
                    "Diogo Neto",
                    "27/07/2001",
                    "Masculino",
                    "Rua Teste n285",
                    "3750-598",
                    938059434,
                    "dvnetoubz@gmail.com",
                    14537834,
                    238316050,
                    pulseira.id)
            )

            db.close()
        }

        @Test
        fun consegueInserirConsulta() {
            val db = getWritableDatabase()

            val pulseira = Pulseira("Laranja")

            val medico = Medico(
                "Teste 1",
                "Consulta",
                928754328,
                "teste@gmail.com",
                "Masculino",
                14537834
            )

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
                238316050,
                pulseira.id
            )

            inserePaciente(db, paciente)


            val consulta = Consulta(
                "07/06/2022",
                "Infantil",
                "Garganta Inflamada",
                15,
                medico.id,
                paciente.id
            )

            insereConsulta(db, consulta)


            db.close()
        }

        @Test
        fun consegueInserirPulseira(){
            val db = getWritableDatabase()

            val pulseira = Pulseira("Vermelho")

            inserePulseira(db, pulseira)

            db.close()
        }

        @Test
        fun consegueAlterarMedico() {
            val db = getWritableDatabase()

            val medico = Medico(
                "Teste",
                "Teste",
                999999999,
                "asd@gmail.com",
                "Feminino",
                11111111
            )
            insereMedico(db, medico)

            medico.nome = "Raul Pereira"
            medico.especialidade = "Cirurgião"

            val registosAlterados = TabelaBDMedicos(db).update(
                medico.toContentValues(),
                "${BaseColumns._ID}=?",
                arrayOf("${medico.id}")
            )

            Assert.assertEquals(1, registosAlterados)

            db.close()
        }

    @Test
    fun consegueAlterarPaciente() {
        val db = getWritableDatabase()

        val pulseira_vermelho = Pulseira("Vermelho")

        val pulseira_amarela = Pulseira("Amarelo")

        val paciente = Paciente(
            "Teste",
            "27/07/2001",
            "Masculino",
            "Rua Teste n285",
            "3750-598",
            123456789,
            "teste@gmail.com",
            87654321,
            834729457,
            pulseira_vermelho.id
        )
        inserePaciente(db, paciente)

        paciente.nome = "Raul Pereira"
        paciente.morada = "Avenida Teste Bloco 2"
        pulseira_vermelho.id = pulseira_amarela.id

        val registosAlterados = TabelaBDPacientes(db).update(
            paciente.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${paciente.id}")
        )

        Assert.assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarConsulta() {
        val db = getWritableDatabase()

        val medicoDomicilio = Medico(
            "João Tavares",
            "Domicilio",
            123456789,
            "teste@gmail.com",
            "Masculino",
            13579246)

        insereMedico(db, medicoDomicilio)

        val medicoCirurgiao = Medico(
            "José Santos",
            "Cirurgião",
            987654321,
            "teste123@gmail.com",
            "Masculino",
            24680135)

        insereMedico(db, medicoCirurgiao)

        val pulseira_crianca = Pulseira("Vermelho")

        val pacienteCrianca = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            938059434,
            "dvnetoubz@gmail.com",
            14537834,
            238316050,
            pulseira_crianca.id)

        inserePaciente(db, pacienteCrianca)

        val pulseira_adulto = Pulseira("Amarelo")

        val pacienteAdulto = Paciente(
            "Rui Pedro",
            "06/08/2001",
            "Masculino",
            "Avenida teste Bloco 2",
            "3750-500",
            933648765,
            "ruipedro@gmail.com",
            15436789,
            867345095,
            pulseira_adulto.id)

        inserePaciente(db, pacienteAdulto)

        val consulta = Consulta(
            "08/06/2022",
            "Infantil",
            "Dores de cabeça e febre",
            10,
            medicoDomicilio.id,
            pacienteCrianca.id)

        insereConsulta(db, consulta)

        consulta.descricao = "vários sintomas COVID-19"
        medicoDomicilio.id = medicoCirurgiao.id



        val registosAlterados = TabelaBDConsultas(db).update(
            consulta.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${consulta.id}"))

        Assert.assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarPulseira() {
        val db = getWritableDatabase()

        val pulseira = Pulseira("Vermelha")
        inserePulseira(db, pulseira)

        pulseira.pulseira = "Amarela"

        val registosAlterados = TabelaBDPulseiras(db).update(pulseira.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${pulseira.id}"))
    }

    @Test
    fun consegueEliminarMedico() {
        val db = getWritableDatabase()

        val medico = Medico(
            "João Tavares",
            "Domicilio",
            123456789,
            "teste@gmail.com",
            "Masculino",
            13579246)
        insereMedico(db, medico)

        val medico2 = Medico(
            "José Santos",
            "Cirurgião",
            987654321,
            "teste123@gmail.com",
            "Masculino",
            24680135)
        insereMedico(db, medico2)

        val registosEliminados = TabelaBDMedicos(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${medico.id}"))

        Assert.assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarPaciente() {
        val db = getWritableDatabase()

        val pulseira = Pulseira("Verde")

        val paciente = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            938059434,
            "dvnetoubz@gmail.com",
            14537834,
            238316050,
            pulseira.id)

        inserePaciente(db, paciente)

        val paciente2 = Paciente(
            "Rui Pedro",
            "06/08/2001",
            "Masculino",
            "Avenida teste Bloco 2",
            "3750-500",
            933648765,
            "ruipedro@gmail.com",
            15436789,
            867345095,
            pulseira.id)

        inserePaciente(db, paciente2)

        val registosEliminados = TabelaBDPacientes(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${paciente.id}"))

        Assert.assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarConsulta() {
        val db = getWritableDatabase()

        val medico = Medico(
            "João Tavares",
            "Domicilio",
            123456789,
            "teste@gmail.com",
            "Masculino",
            13579246)
        insereMedico(db, medico)

        val pulseira = Pulseira("Azul")

        val paciente = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            938059434,
            "dvnetoubz@gmail.com",
            14537834,
            238316050,
            pulseira.id)
        inserePaciente(db, paciente)

        val consulta = Consulta(
            "27/07/2022",
            "Urgência",
            "Paciente com perna partida",
            10,
            medico.id,
            paciente.id)
        insereConsulta(db, consulta)

        val registosEliminados = TabelaBDConsultas(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${consulta.id}"))

        Assert.assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarPulseiras(){
        val db = getWritableDatabase()

        val pulseira = Pulseira("Vermelho")
        inserePulseira(db, pulseira)

        val registosEliminados = TabelaBDPulseiras(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${pulseira.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerMedicos() {
        val db = getWritableDatabase()

        val medico = Medico("Rogério Alves",
        "Infantil",
        934765437,
        "rogerioalvez@gmail.com",
        "Masculino",
        14765478)
        insereMedico(db, medico)

        val cursor = TabelaBDMedicos(db).query(
            TabelaBDMedicos.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${medico.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val medicoBD = Medico.fromCursor(cursor)

        assertEquals(medico, medicoBD)
    }

    @Test
    fun consegueLerPacientes() {
        val db = getWritableDatabase()

        val pulseira = Pulseira("Verde")

        val paciente = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            938059434,
            "dvnetoubz@gmail.com",
            14537834,
            238316050,
            pulseira.id)
        inserePaciente(db, paciente)

        val cursor = TabelaBDPacientes(db).query(
            TabelaBDPacientes.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${paciente.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val pacienteBD = Paciente.fromCursor(cursor)

        assertEquals(paciente, pacienteBD)
    }

    @Test
    fun consegueLerConsultas() {
        val db = getWritableDatabase()

        val medico = Medico("Rogério Alves",
            "Infantil",
            934765437,
            "rogerioalvez@gmail.com",
            "Masculino",
            14765478)
        insereMedico(db, medico)

        val pulseira = Pulseira("Laranja")

        val paciente = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            938059434,
            "dvnetoubz@gmail.com",
            14537834,
            238316050,
            pulseira.id)
        inserePaciente(db, paciente)

        val consulta = Consulta(
            "27/07/2022",
            "Urgência",
            "Paciente com perna partida",
            10,
            medico.id,
            paciente.id)
        insereConsulta(db, consulta)

        val cursor = TabelaBDConsultas(db).query(
            TabelaBDConsultas.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${consulta.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val consultaBD = Consulta.fromCursor(cursor)

        assertEquals(consulta, consultaBD)

        db.close()
    }

    @Test
    fun consegueLerPulseira() {
        val db = getWritableDatabase()

        val pulseira = Pulseira("Verde")
        inserePulseira(db, pulseira)

        val cursor = TabelaBDPulseiras(db).query(
            TabelaBDPulseiras.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${pulseira.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val pulseiraBD = Pulseira.fromCursor(cursor)

        assertEquals(pulseira, pulseiraBD)
    }

}