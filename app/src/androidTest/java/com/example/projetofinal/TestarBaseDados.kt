package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
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

    }

    private fun inserePaciente(db: SQLiteDatabase, paciente: Paciente) {
        paciente.id = TabelaBDPacientes(db).insert(paciente.toContentValues())

    }

    private fun insereConsulta(db: SQLiteDatabase, consulta: Consulta) {
        consulta.id = TabelaBDConsultas(db).insert(consulta.toContentValues())

    }

    private fun inserePulseira(db: SQLiteDatabase, pulseira: Pulseira){
        pulseira.id = TabelaBDPulseiras(db).insert(pulseira.toContentValues())
    }

    private fun insereEspecialidade(db: SQLiteDatabase, especialidade: Especialidade){
        especialidade.id = TabelaBDEspecialidades(db).insert(especialidade.toContentValues())
    }

    @Before
    fun apagaBaseDados() {
        //appContext().deleteDatabase(BDOpenHelper.NOME)
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

        val especialidade = Especialidade("Domicilio")
        insereEspecialidade(db,especialidade)

        insereMedico(
            db, Medico(
                "Teste 1",
                "934563467",
                "teste@gmail.com",
                "Masculino",
                "14765478",
                especialidade
            )
        )
    }

        @Test
        fun consegueInserirPaciente() {
            val db = getWritableDatabase()

            inserePaciente(
                db, Paciente(
                    "Diogo Neto",
                    "27/07/2001",
                    "Masculino",
                    "Rua Teste n285",
                    "3750-598",
                    "938059434",
                    "dvnetoubz@gmail.com",
                    "14537834",
                    "238316050")
            )

            db.close()
        }

        @Test
        fun consegueInserirConsulta() {
            val db = getWritableDatabase()

            val especialidade = Especialidade("Cardiologista")
            insereEspecialidade(db,especialidade)

            val medico = Medico(
                "Teste 1",
                "934563467",
                "teste@gmail.com",
                "Masculino",
                "14765478",
                especialidade
            )
            insereMedico(db, medico)

            val paciente = Paciente(
                "Diogo Neto123",
                "27/07/2001",
                "Masculino",
                "Rua Teste n285",
                "3750-598",
                "938059434",
                "dvnetoubz@gmail.com",
                "14537834",
                "238316050"
            )
            inserePaciente(db, paciente)

            val pulseira = Pulseira("Amarela")
            inserePulseira(db, pulseira)

            val consulta = Consulta(
                12052022,
                "Garganta Inflamada",
                "10.00",
                medico,
                paciente,
                pulseira
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
        fun consegueInserirEspecialidade(){
            val db = getWritableDatabase()

            val especialidade = Especialidade("Cardiologista")

            insereEspecialidade(db,especialidade)

            db.close()
        }

        @Test
        fun consegueAlterarMedico() {
            val db = getWritableDatabase()

            val especialidade_cardiologista = Especialidade("Cardiologista")
            insereEspecialidade(db,especialidade_cardiologista)

            val especialidade_cirurgiao = Especialidade("Cirurgião")
            insereEspecialidade(db, especialidade_cirurgiao)

            val medico = Medico(
                "Teste",
                "934563467",
                "asd@gmail.com",
                "Feminino",
                "14765478",
                especialidade_cardiologista
            )
            insereMedico(db, medico)

            medico.nome = "Raul Pereira"
            medico.especialidade = especialidade_cirurgiao

            val registosAlterados = TabelaBDMedicos(db).update(
                medico.toContentValues(),
                "${TabelaBDMedicos.CAMPO_ID}=?",
                arrayOf("${medico.id}")
            )

            Assert.assertEquals(1, registosAlterados)

            db.close()
        }

    @Test
    fun consegueAlterarPaciente() {
        val db = getWritableDatabase()

        val paciente = Paciente(
            "Teste",
            "27/07/2001",
            "Masculino",
            "Rua Teste n285",
            "3750-598",
            "123456789",
            "teste@gmail.com",
            "87654321",
            "834729457"
        )
        inserePaciente(db, paciente)

        paciente.nome = "Raul Pereira"
        paciente.morada = "Avenida Teste Bloco 2"

        val registosAlterados = TabelaBDPacientes(db).update(
            paciente.toContentValues(),
            "${TabelaBDPacientes.CAMPO_ID}=?",
            arrayOf("${paciente.id}")
        )

        Assert.assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueAlterarConsulta() {
        val db = getWritableDatabase()

        val especialidade_domicilio = Especialidade("Domicilio")
        insereEspecialidade(db,especialidade_domicilio)

        val medicoDomicilio = Medico(
            "João Tavares",
            "934563467",
            "teste@gmail.com",
            "Masculino",
            "14765478",
            especialidade_domicilio)
        insereMedico(db, medicoDomicilio)

        val especialidade_cirurgiao = Especialidade("Cirurgião")
        insereEspecialidade(db,especialidade_cirurgiao)

        val medicoCirurgiao = Medico(
            "José Santos",
            "934563467",
            "teste123@gmail.com",
            "Masculino",
            "14765478",
        especialidade_cirurgiao)

        insereMedico(db, medicoCirurgiao)

        val pacienteCrianca = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            "938059434",
            "dvnetoubz@gmail.com",
            "14537834",
            "238316050")

        inserePaciente(db, pacienteCrianca)


        val pacienteAdulto = Paciente(
            "Rui Pedro",
            "06/08/2001",
            "Masculino",
            "Avenida teste Bloco 2",
            "3750-500",
            "933648765",
            "ruipedro@gmail.com",
            "15436789",
            "867345095")

        inserePaciente(db, pacienteAdulto)

        val pulseira = Pulseira("Amarela")
        inserePulseira(db, pulseira)

        val pulseira2 = Pulseira("Vermelho")
        inserePulseira(db, pulseira2)

        val consulta = Consulta(
            12052022,
            "Dores de cabeça e febre",
            "10.00",
            medicoDomicilio,
            pacienteCrianca,
            pulseira)

        insereConsulta(db, consulta)

        consulta.descricao = "vários sintomas COVID-19"
        medicoDomicilio.id = medicoCirurgiao.id
        pulseira.id = pulseira2.id



        val registosAlterados = TabelaBDConsultas(db).update(
            consulta.toContentValues(),
            "${TabelaBDConsultas.CAMPO_ID}=?",
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
            "${TabelaBDPulseiras.CAMPO_ID}=?",
            arrayOf("${pulseira.id}"))
    }

    @Test
    fun consegueAlterarEspecialidade(){
        val db = getWritableDatabase()

        val especialidade = Especialidade("Cardiologista")
        insereEspecialidade(db, especialidade)

        especialidade.especialidade = "Cirurgião"

        val registosAlterados = TabelaBDEspecialidades(db).update(especialidade.toContentValues(),
            "${TabelaBDEspecialidades.CAMPO_ID}=?",
            arrayOf("${especialidade.id}"))
    }

    @Test
    fun consegueEliminarMedico() {
        val db = getWritableDatabase()

        val especialidade_cardiologista = Especialidade("Cardiologista")
        insereEspecialidade(db,especialidade_cardiologista)

        val medico = Medico(
            "João Tavares",
            "934563467",
            "teste@gmail.com",
            "Masculino",
            "14765478",
            especialidade_cardiologista)
        insereMedico(db, medico)

        val especialidade_cirurgiao = Especialidade("Cirurgião")
        insereEspecialidade(db,especialidade_cirurgiao)

        val medico2 = Medico(
            "José Santos",
            "934563467",
            "teste123@gmail.com",
            "Masculino",
            "14765478",
            especialidade_cirurgiao)
        insereMedico(db, medico2)

        val registosEliminados = TabelaBDMedicos(db).delete(
            "${TabelaBDMedicos.CAMPO_ID}=?",
            arrayOf("${medico.id}"))

        Assert.assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarPaciente() {
        val db = getWritableDatabase()

        val paciente = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            "938059434",
            "dvnetoubz@gmail.com",
            "14537834",
            "238316050")

        inserePaciente(db, paciente)

        val paciente2 = Paciente(
            "Rui Pedro",
            "06/08/2001",
            "Masculino",
            "Avenida teste Bloco 2",
            "3750-500",
            "933648765",
            "ruipedro@gmail.com",
            "15436789",
            "867345095")

        inserePaciente(db, paciente2)

        val registosEliminados = TabelaBDPacientes(db).delete(
            "${TabelaBDPacientes.CAMPO_ID}=?",
            arrayOf("${paciente.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarConsulta() {
        val db = getWritableDatabase()

        val especialidade = Especialidade("Cardiologista")
        insereEspecialidade(db,especialidade)

        val medico = Medico(
            "João Tavares",
            "934563467",
            "teste@gmail.com",
            "Masculino",
            "14765478",
            especialidade)
        insereMedico(db, medico)

        val paciente = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            "938059434",
            "dvnetoubz@gmail.com",
            "14537834",
            "238316050")
        inserePaciente(db, paciente)

        val pulseira = Pulseira("Amarela")
        inserePulseira(db, pulseira)

        val consulta = Consulta(
            27072022,
            "Paciente com perna partida",
            "10.00",
            medico,
            paciente,
            pulseira)
        insereConsulta(db, consulta)

        val registosEliminados = TabelaBDConsultas(db).delete(
            "${TabelaBDConsultas.CAMPO_ID}=?",
            arrayOf("${consulta.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarPulseiras(){
        val db = getWritableDatabase()

        val pulseira = Pulseira("Vermelho")
        inserePulseira(db, pulseira)

        val registosEliminados = TabelaBDPulseiras(db).delete(
            "${TabelaBDPulseiras.CAMPO_ID}=?",
            arrayOf("${pulseira.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueEliminarEspecialidades() {
        val db = getWritableDatabase()

        val especialidade = Especialidade("Domicilio")
        insereEspecialidade(db, especialidade)

        val registosEliminados = TabelaBDEspecialidades(db).delete(
            "${TabelaBDEspecialidades.CAMPO_ID}=?",
            arrayOf("${especialidade.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerMedicos() {
        val db = getWritableDatabase()

        val especialidade = Especialidade("Cardiologista")
        insereEspecialidade(db,especialidade)

        val medico = Medico("Diogo Neto",
            "934563467",
        "rogerioalvez@gmail.com",
        "Masculino",
            "14765478",
        especialidade)
        insereMedico(db, medico)

        val cursor = TabelaBDMedicos(db).query(
            TabelaBDMedicos.TODAS_COLUNAS,
            "${TabelaBDMedicos.CAMPO_ID}=?",
            arrayOf("${medico.id}"),
            null,
            null,
            null
        )

    }

    @Test
    fun consegueLerPacientes() {
        val db = getWritableDatabase()

        val paciente = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            "938059434",
            "dvnetoubz@gmail.com",
            "14537834",
            "238316050")
        inserePaciente(db, paciente)

        val cursor = TabelaBDPacientes(db).query(
            TabelaBDPacientes.TODAS_COLUNAS,
            "${TabelaBDPacientes.CAMPO_ID}=?",
            arrayOf("${paciente.id}"),
            null,
            null,
            null
        )

    }

    @Test
    fun consegueLerConsultas() {
        val db = getWritableDatabase()

        val especialidade = Especialidade("Cardiologista")
        insereEspecialidade(db, especialidade)

        val medico = Medico("Rogério Alves",
            "934563467",
            "rogerioalvez@gmail.com",
            "Masculino",
            "14765478",
            especialidade)
        insereMedico(db, medico)

        val paciente = Paciente(
            "Diogo Neto",
            "27/07/2001",
            "Masculino",
            "Rua teste n285",
            "3750-598",
            "938059434",
            "dvnetoubz@gmail.com",
            "14537834",
            "238316050")
        inserePaciente(db, paciente)

        val pulseira = Pulseira("Amarela")
        inserePulseira(db, pulseira)

        val consulta = Consulta(
            12052022,
            "Paciente com perna partida",
            "10.00",
            medico,
            paciente,
            pulseira)
        insereConsulta(db, consulta)

        val cursor = TabelaBDConsultas(db).query(
            TabelaBDConsultas.TODAS_COLUNAS,
            "${TabelaBDConsultas.CAMPO_ID}=?",
            arrayOf("${consulta.id}"),
            null,
            null,
            null
        )

    }

    @Test
    fun consegueLerPulseira() {
        val db = getWritableDatabase()

        val pulseira = Pulseira("Verde")
        inserePulseira(db, pulseira)

        val cursor = TabelaBDPulseiras(db).query(
            TabelaBDPulseiras.TODAS_COLUNAS,
            "${TabelaBDPulseiras.CAMPO_ID}=?",
            arrayOf("${pulseira.id}"),
            null,
            null,
            null
        )

    }

    @Test
    fun consegueLerEspecialidade(){
        val db = getWritableDatabase()

        val especialidade = Especialidade("Cirurgião")
        insereEspecialidade(db, especialidade)

        val cursor = TabelaBDEspecialidades(db).query(
            TabelaBDEspecialidades.TODAS_COLUNAS,
            "${TabelaBDEspecialidades.CAMPO_ID}=?",
            arrayOf("${especialidade.id}"),
            null,
            null,
            null
        )

    }

}