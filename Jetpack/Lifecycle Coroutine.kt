package com.kings.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.kings.project.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// DEPENDENCIA: implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnThread1.setOnClickListener {

            // JETPACK COROUTINE - FINALIZA ACAO AUTOMATICAMENTE QUANDO FECHA/TROCA A TELA
            lifecycleScope.launch {
                repeat(10) {
                    Log.i("testeJetpack", "indice: $it")
                    delay(1000)
                }
            }
        }
    }
}
