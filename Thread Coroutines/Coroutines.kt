package com.kings.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kings.project.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // RETORNO DO LAUNCH DE UMA COROUTINE - USADO PARA ALGUMAS FUNCOES COMO PARAR A ACAO
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // BOTAO 1 VAI INICIAR A COROUTINE 1
        binding.btnThread1.setOnClickListener {

            // ATALHOS DE COROUTINES:
            // MainScope.launch {  } - Utilizado para alterar itens na Main
            // GlobalScope.launch {  } - Utilizado para buscar dados
            // lifecycleScope.launch {  } / JETPACK - Usado para destruir automaticamente a acao quando sair da tela

            job = CoroutineScope(Dispatchers.IO).launch {

                // UM TIPO DE COROUTINE (nao recomendado)
                runBlocking {
                    binding.btnThread1.isEnabled = false
                }

                // ACAO DE REPETICAO COM DELAY
                repeat(5) { indice ->

                    // ABRINDO O CONTEXTO DA COROUTINE MAIN - SOMENTE NA MAIN PODE FAZER ALTERACOES
                    withContext(Dispatchers.Main) {
                        binding.txtThread1.text = "Coroutine 1 indice: $indice"
                    }
                    delay(1000)
                }

                // CRIAR UMA NOVA COROUTINE DENTRO DE UMA COROUTINE
                launch {
                    teste()
                }

                val resultado1 = async { tarefa1() }
                val resultado2 = async { tarefa2() }

                // NAO VAI RETORNAR O VALOR CORRETO POIS NAO ESPEROU O FINAL DA TAREFA
                Log.i("TesteCoroutine", "Tarefa1 resultado: $resultado1")

                // METODO await() ESPERA A FINALIZACAO DA ACAO tarefa2() PARA PEGAR O RESULTADO
                Log.i("TesteCoroutine", "Tarefa2 resultado: ${resultado2.await()}")

                binding.btnThread1.isEnabled = true
            }

        }

        // BOTAO 2 VAI INICIAR A COROUTINE 2
        binding.btnThread2.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {

                // DEFINIR UM TEMPO MAXIMO PARA AS ACOES
                withTimeout(30000L) {
                    executar(binding)
                }
            }
        }

        binding.btnParar.setOnClickListener {
            // METODO PARA FINALIZAR A COROUTINE
            job?.cancel()
        }
    }

    private suspend fun tarefa1(): String {
        repeat(30) {
            Log.i("TesteCoroutine", "Tarefa1 indice: $it")
            delay(1000)
        }
        return "finalizou tarefa1"
    }

    private suspend fun tarefa2(): String {
        repeat(30) {
            Log.i("TesteCoroutine", "Tarefa2 indice: $it")
            delay(2000)
        }
        return "finalizou tarefa2"
    }

    private suspend fun teste() {
        delay(1000)
        Log.i("TesteCoroutine", "metodo teste")
    }

    private suspend fun executar(binding: ActivityMainBinding) {
        repeat(30) {
            binding.txtThread2.text = "Coroutine 2 indice: $it"

            // METODO DELAY SO PODE SER USADO EM FUNCAO `SUSPEND`
            delay(2000)
        }
    }
}
