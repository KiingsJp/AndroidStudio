package com.kings.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var pokedexView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokedexView = findViewById(R.id.rv_pokedex)
        pokedexView.adapter = PokedexAdapter(createPokemons())
        pokedexView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun createPokemons(): List<Pokemon> {
        return listOf(
            Pokemon(
                "Bulbasaur",
                "Grass",
                R.drawable.bulbasaur,
                "Ivysaur"
            ),
            Pokemon(
                "Charmander",
                "Fire",
                R.drawable.charmander,
                "Charmeleon"
            ),
            Pokemon(
                "Squirtle",
                "Water",
                R.drawable.squirtle,
                "Wartortle"
            ),
            Pokemon("Pikachu",
                "Electric",
                R.drawable.pikachu,
                "Raichu"
            )
        )
    }
}
