package com.kings.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PokedexAdapter(val pokemons: List<Pokemon>) : Adapter<PokedexAdapter.PokedexAdapterHolder>() {

    inner class PokedexAdapterHolder(val view: View) : ViewHolder(view) {
        val nomePokemon: TextView = view.findViewById(R.id.text_nome)
        val tipoPokemon: TextView = view.findViewById(R.id.text_tipo)
        val imgPokemon: ImageView = view.findViewById(R.id.img_pokemon)
        val evolucaoPokemon: TextView = view.findViewById(R.id.text_evolucao)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexAdapterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pokedex, parent, false)
        return PokedexAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: PokedexAdapterHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.nomePokemon.text = pokemon.nomePokemon
        holder.imgPokemon.setImageResource(pokemon.imgPokemon)
        holder.tipoPokemon.text = pokemon.tipoPokemon
        holder.evolucaoPokemon.text = pokemon.evolucaoPokemon
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }
}