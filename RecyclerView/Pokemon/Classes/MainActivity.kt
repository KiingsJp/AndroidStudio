package com.kings.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var pokedexView: RecyclerView
    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var imgPokebola: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgPokebola = findViewById(R.id.imgv_pokebola)

        pokedexAdapter = PokedexAdapter { pokemon ->
            val intent = Intent(this, PokeActivity::class.java)
            intent.putExtra("pokemon", pokemon)
            startActivity(intent)
        }
        pokedexAdapter.atualizarLista(createPokemons())

        pokedexView = findViewById(R.id.rv_pokedex)
        pokedexView.adapter = pokedexAdapter
        pokedexView.layoutManager = GridLayoutManager(this, 2)

        pokedexView.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )

        imgPokebola.setOnClickListener {
            pokedexAdapter.atualizarLista(
                mutableListOf(
                    Pokemon(
                        "Pikachu",
                        "Electric",
                        R.drawable.pikachu,
                        "Raichu",
                        mutableListOf(
                            Pokemon("Pikachu", "Electric", R.drawable.pikachu, "Raichu"),
                            Pokemon("Raichu", "Electric", R.drawable.raichu, "N/A")
                        )
                    )
                )
            )
        }
    }

    private fun createPokemons(): MutableList<Pokemon> {
        return mutableListOf(
            Pokemon(
                "Bulbasaur",
                "Grass",
                R.drawable.bulbasaur,
                "Ivysaur",
                mutableListOf(
                    Pokemon("Bulbasaur", "Grass", R.drawable.bulbasaur, "Ivysaur"),
                    Pokemon("Ivysaur", "Grass", R.drawable.ivysaur, "Venosaur"),
                    Pokemon("Venosaur", "Grass", R.drawable.venusaur, "N/A")
                )
            ),
            Pokemon(
                "Charmander",
                "Fire",
                R.drawable.charmander,
                "Charmeleon",
                mutableListOf(
                    Pokemon("Charmander", "Fire", R.drawable.charmander, "Charmeleon"),
                    Pokemon("Charmeleon", "Fire", R.drawable.charmeleon, "Charizard"),
                    Pokemon("Charizard", "Fire", R.drawable.charizard, "N/A")
                )
            ),
            Pokemon(
                "Squirtle",
                "Water",
                R.drawable.squirtle,
                "Wartortle",
                mutableListOf(
                    Pokemon("Squirtle", "Water", R.drawable.squirtle, "Wartortle"),
                    Pokemon("Wartortle", "Water", R.drawable.wartortle, "Blastoise"),
                    Pokemon("Blastoise", "Water", R.drawable.blastoise, "N/A")
                )
            )
        )
    }
}