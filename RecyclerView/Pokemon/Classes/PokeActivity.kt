package com.kings.pokedex

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PokeActivity : AppCompatActivity() {

    private lateinit var rvView: RecyclerView
    private lateinit var pokedexAdapter: PokedexAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poke)

        val pokemon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("pokemon", Pokemon::class.java)
        } else
            intent.getSerializableExtra("pokemon") as Pokemon

        rvView = findViewById(R.id.rv_evolution)
        if (pokemon != null) {
            pokedexAdapter = PokedexAdapter {pokemonAtual -> Toast.makeText(this, pokemonAtual.nomePokemon, Toast.LENGTH_SHORT).show() }
            pokemon.evolution?.let { evolution-> pokedexAdapter.atualizarLista(evolution) }
            rvView.adapter = pokedexAdapter
        }
        rvView.layoutManager = LinearLayoutManager(this)
        rvView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

    }
}