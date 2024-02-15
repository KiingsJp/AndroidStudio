package com.kings.pokedex

import java.io.Serializable

data class Pokemon(
    val nomePokemon: String,
    val tipoPokemon: String,
    val imgPokemon: Int,
    val evolucaoPokemon: String,
    val evolution: MutableList<Pokemon>? = null
) : Serializable
