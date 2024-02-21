package com.kings.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.kings.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.includeToolbar.toolbar.inflateMenu(R.menu.menu_actionbar)
        binding.includeToolbar.toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_item_adicionar -> {
                    Toast.makeText(applicationContext, "Adicionar", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnMenuItemClickListener true
        }

        // USAR TOOLBAR NA ACTIONBAR:
        // setSupportActionBar(binding.includeToolbar.toolbar)

        // DEFINIR BOTAO DE VOLTAR:
        // em AndroidManifest adicione na sua activity qual tela que o botao voltar deve ir = android:parentActivityName=".ActivityHome"
        // supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}