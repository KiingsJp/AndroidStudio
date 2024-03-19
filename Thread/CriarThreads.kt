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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val thread = MinhaThread1()
        thread.start()

        MinhaThread2().start()
    }

    inner class MinhaThread1() : Thread() {
        override fun run() {
            super.run()
            executarAlgo()
        }

        private fun executarAlgo() {
            repeat(30) {
                Log.i("Kings.Jp", "Thread 1: $it")
                sleep(1000)
            }
        }
    }

    inner class MinhaThread2() : Thread() {
        override fun run() {
            super.run()
            executarAlgo()
        }

        private fun executarAlgo() {
            repeat(30) {
                Log.i("Kings.Jp", "Thread 2: ${it+100}")
                sleep(2000)
            }
        }
    }
}