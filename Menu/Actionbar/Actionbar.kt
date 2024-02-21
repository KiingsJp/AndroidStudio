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

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        // Em themes.xml APAGUE O ".NoActionBar" para ser possivel a visualizacao

        // METODO 1 - MAIS UTILIZADO
        addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_actionbar, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when(menuItem.itemId) {
                        R.id.menu_item_adicionar -> Toast.makeText(applicationContext, "Adicionar", Toast.LENGTH_SHORT).show()
                    }
                    return true
                }
            }
        )
    }

    // Em themes.xml APAGUE O ".NoActionBar" para ser possivel a visualizacao

    // METODO 2 - MENOS UTILIZADO
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_actionbar, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.menu_item_adicionar -> Toast.makeText(this, "Adicionar", Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}