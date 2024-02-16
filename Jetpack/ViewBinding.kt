package com.kings.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kings.jetpack.databinding.ActivityMainBinding

// PARA ATIVAR O VIEW BINDING:
// build.gradle.kts (Module: app)
// adicione dentro de android { ... }
// buildFeatures{ viewBinding = true }
// em versoes antigas: android { ... viewBinding { enable = true }  }

// Padrao nomeclatura
// layout: activity_main.xml
// binding: AcitivyMainBinding
// layout: pokemon_status.xml
// binding: PokemonStatusBinding

class MainActivity : AppCompatActivity() {

    // CRIA O BINDING E JA FAZ A INICIALIZACAO DELE
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main) -> DEVE SER TROCADO PARA O LAYOUT DO BINDING
        setContentView(binding.root)

        // AGORA TODOS OS ITENS DO LAYOUT JA PODEM SER ACESSADOS
        binding.btnClique1.setOnClickListener {
            Toast.makeText(this, "Botao 1", Toast.LENGTH_SHORT).show()
        }

        // OUTRA MANEIRA DE ACESSAR OS ITENS:
        with(binding){
            btnClique2.setOnClickListener {
                Toast.makeText(it.context, "Botao 2", Toast.LENGTH_SHORT).show()
            }

            textHello.text = "Hello World!"
        }
    }
}