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

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var pararThread: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            // BOTAO PARA PARAR A THREAD
            btnParar.setOnClickListener {
                pararThread = true
            }

            // BOTAO 1 VAI INICIAR A THREAD 1
            btnThread1.setOnClickListener {

                // INSTANCIAR A THREAD E INICIAR
                val thread = MinhaThread1()
                thread.start()
            }

            // BOTAO 2 VAI INICIAR A THREAD 2
            btnThread2.setOnClickListener {

                // CRIAR THREAD USANDO EXPRESSAO LAMBDA
                Thread {
                    pararThread = false
                    repeat(30) {
                        Thread.sleep(2000)
                        runOnUiThread {

                            // TRATATIVA PARA O BOTAO DE PARAR A THREAD
                            if(pararThread) {
                                runOnUiThread {
                                    binding.txtThread2.text = "Thread 2 parada"
                                    binding.btnThread2.isEnabled = true
                                }

                                // SEM ESSA ACAO A THREAD APENAS PAUSA (EXEMPLO THREAD 1), COM O INTERRUPTED ELA FINALIZA (EXEMPLO THREAD 2)
                                Thread.interrupted()
                                return@runOnUiThread
                            }

                            binding.txtThread2.text = it.toString()
                            binding.btnThread2.isEnabled = false
                            if (it == 29) {
                                binding.btnThread2.isEnabled = true
                            }
                        }
                    }
                }.start()
            }
        }
    }

    inner class MinhaThread1 : Thread() {
        override fun run() {
            super.run()
            executarAlgo()
        }

        private fun executarAlgo() {
            pararThread = false
            repeat(30) {
                sleep(1000)

                // TRATATIVA PARA O BOTAO DE PARAR A THREAD
                if(pararThread) {
                    runOnUiThread{
                        binding.txtThread1.text = "Thread 1 parada"
                        binding.btnThread1.isEnabled = true
                    }
                    return
                }

                runOnUiThread {
                    binding.txtThread1.text = it.toString()
                    binding.btnThread1.isEnabled = false
                    if (it == 29) {
                        binding.btnThread1.isEnabled = true
                    }
                }
            }
        }
    }
}