package com.kings.project

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuProvider
import com.google.android.material.snackbar.Snackbar
import com.kings.project.database.DatabaseHelper
import com.kings.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        DatabaseHelper(this)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnSalvar.setOnClickListener {
                salvar()
            }

            btnAtualizar.setOnClickListener {
                atualizar()
            }

            btnRemover.setOnClickListener {
                remover()
            }

            btnListar.setOnClickListener {
                listar()
            }
        }
    }

    private fun listar() {
        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS}"
        val cursor = db.readableDatabase.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex(DatabaseHelper.ID_PRODUTO)
        val indiceNome = cursor.getColumnIndex(DatabaseHelper.NOME_PRODUTO)
        val indiceValor = cursor.getColumnIndex(DatabaseHelper.VALOR_PRODUTO)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(indiceId)
            val nome = cursor.getString(indiceNome)
            val valor = cursor.getFloat(indiceValor)

            Log.i("Valores", "id: $id - nome: $nome - valor: $valor")
        }
    }

    private fun atualizar() {
        val valores = ContentValues().let { valores ->
            valores.put(DatabaseHelper.NOME_PRODUTO, "Produto Atualizado")
            valores.put(DatabaseHelper.VALOR_PRODUTO, 1500.00)
            valores
        }
        val args = arrayOf<String>("3", "3900.00")
        db.writableDatabase.update(
            DatabaseHelper.TABELA_PRODUTOS,
            valores, "${DatabaseHelper.ID_PRODUTO} = ? AND ${DatabaseHelper.VALOR_PRODUTO} = ?",
            args
        )

        // OUTRO METODO:
//      db.writableDatabase.execSQL("UPDATE ${DatabaseHelper.TABELA_PRODUTOS} SET VALOR = 4500 WHERE ID = 1")
    }

    private fun remover() {
        val args = arrayOf<String>("5", "3900")
        db.writableDatabase.delete(
            DatabaseHelper.TABELA_PRODUTOS,
            "${DatabaseHelper.ID_PRODUTO} = ? AND ${DatabaseHelper.VALOR_PRODUTO} = ?",
            args
        )

        // OUTRO METODO:
//      db.writableDatabase.execSQL("DELETE FROM ${DatabaseHelper.TABELA_PRODUTOS} WHERE ID = 1")
    }

    private fun salvar() {
        val produto = binding.textProduto.text
        val valores = ContentValues().let { valores ->
            valores.put(DatabaseHelper.NOME_PRODUTO, produto.toString())
            valores.put(DatabaseHelper.VALOR_PRODUTO, 3000.00)
            valores
        }
        db.writableDatabase.insert(DatabaseHelper.TABELA_PRODUTOS, null, valores)
        binding.textProduto.text.clear()

        // OUTRO METODO:
//      db.writableDatabase.execSQL("INSERT INTO ${DatabaseHelper.TABELA_PRODUTOS} VALUES (null, '$produto', 3900.00)")
    }
}