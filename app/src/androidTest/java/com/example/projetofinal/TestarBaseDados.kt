package com.example.projetofinal

import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
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

        val categoria = Medico(
            "Américo Vaz",
            "Cirurgião",
            923654766,
            "teste@gmail.com",
            "Masculino",
            14537834,)

        TabelaBDMedicos(db).insert(categoria.toContentValues())

        db.close()
    }
}