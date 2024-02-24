package com.kings.project.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class DatabaseHelper(private val context: Context): SQLiteOpenHelper(context,"project.db", null, 1) {

    companion object {
        const val TABELA_PRODUTOS = "PRODUTOS"
        const val ID_PRODUTO = "ID"
        const val NOME_PRODUTO = "NOME"
        const val VALOR_PRODUTO = "VALOR"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE $TABELA_PRODUTOS (" +
                "  $ID_PRODUTO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  $NOME_PRODUTO VARCHAR(50) NOT NULL," +
                "  $VALOR_PRODUTO FLOAT NOT NULL" +
                ");"
        try {
            db?.execSQL(sql)
            Log.i("DB.1","BANCO CRIADO")
        } catch (e: Exception) {
            Log.i("DB.1","ERRO NO BANCO: ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}
