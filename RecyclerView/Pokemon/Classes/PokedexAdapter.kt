package com.kings.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PokedexAdapter( private val clique: (pokemon: Pokemon)-> Unit) : Adapter<PokedexAdapter.PokedexAdapterHolder>() {

    private var pokemons = mutableListOf<Pokemon>()

    fun atualizarLista(lista: MutableList<Pokemon>){
        pokemons.addAll(lista)

//         ATUALIZA O ITEM PELA POSICAO INSERIDA
        notifyItemInserted(itemCount)

//         ESCOLHE UMA POSICAO E UMA QUANTIDADE PARA ATUALIZAR
//        notifyItemRangeInserted(2,3)

//         ATUALIZA TODA A GRADE
//        notifyDataSetChanged()
    }

    // PARA O VIEWBINDING, UTILIZE:
    // inner class PokedexAdapterHolder(val binding: CardPokemonBinding) : ViewHolder( binding.root ) { binding.nomePokemon.text = pokemon.nomePokemon  }

    inner class PokedexAdapterHolder(view: View) : ViewHolder(view) {
        private val nomePokemon: TextView = view.findViewById(R.id.text_nome)
        private val tipoPokemon: TextView = view.findViewById(R.id.text_tipo)
        private val imgPokemon: ImageView = view.findViewById(R.id.img_pokemon)
        private val evolucaoPokemon: TextView = view.findViewById(R.id.text_evolucao)
        private val cardView: CardView = view.findViewById(R.id.cardv_pokemon)

        fun bind( pokemon : Pokemon ) {
            nomePokemon.text = pokemon.nomePokemon
            imgPokemon.setImageResource(pokemon.imgPokemon)
            tipoPokemon.text = pokemon.tipoPokemon
            evolucaoPokemon.text = pokemon.evolucaoPokemon

            cardView.setOnClickListener {
               clique(pokemon)
            }
        }
    }

    // PARA O VIEWBINDING, UTILIZE:
    // onCreateViewHold(parent: ViewGroup, viewType: Int): PokedexAdapterHolder { val binding = CardPokemonBinding.inflate( layoutInflater, parent, false); return PokedexAdapterHolder(binding); }
 
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexAdapterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_pokemon, parent, false)
        return PokedexAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: PokedexAdapterHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }
}
