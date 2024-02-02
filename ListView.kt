package com.kings.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var listViewTeste: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewTeste = findViewById(R.id.list_view)
        listViewTeste.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listOf("Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 4")
        )
    }
}
