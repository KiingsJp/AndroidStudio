package com.systemskings.firstprojectfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.systemskings.firstprojectfirebase.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val autentication: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onResume() {
        super.onResume()
        val usuarioLogado = autentication.currentUser?.email ?: "Nenhum usuário Logado"
        binding.txtUsuLogado.text = usuarioLogado
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnCriarUsuario.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val senha = binding.txtSenha.text.toString()
            loginUsuario(email, senha)
        }

        binding.btnLogout.setOnClickListener {
            logOut()
        }
    }

    fun logOut() {
        if (autentication.currentUser != null) {
            autentication.signOut()
            binding.txtResultado.text = "Feito sing out"
        } else {
            binding.txtResultado.text = "Não existe usuário logado"
        }
    }

    fun loginUsuario(email: String, senha: String) {
        autentication.signInWithEmailAndPassword(email, senha)
            .addOnFailureListener {
                binding.txtResultado.text = "Erro ao logar: ${it.message}"
            }.addOnCompleteListener {
                if (autentication.currentUser != null) {
                    val intent = Intent(this, InicioActivity::class.java)
                    startActivity(intent)
                }
            }
    }
}