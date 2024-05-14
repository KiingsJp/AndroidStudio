package com.systemskings.firstprojectfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.systemskings.firstprojectfirebase.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {

    private val binding: ActivityInicioBinding by lazy {
        ActivityInicioBinding.inflate(layoutInflater)
    }

    private val autentication: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val fireStore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getDataUser()

        binding.btnClose.setOnClickListener {
            autentication.signOut()
            fecharActivity()
        }

    }

    fun getDataUser() {
        autentication.currentUser?.uid?.let {
            val uid = it

            fireStore.collection("usuarios")
                .document(uid)
                .addSnapshotListener { value, _ ->
                    val dados = value?.data
                    if (dados != null) {
                        binding.txtEmailIni.text = dados["email"].toString()
                        binding.txtIdadeIni.text = dados["idade"].toString()
                        binding.txtNomeIni.text = dados["nome"].toString()
                        binding.txtUidIni.text = it
                        Picasso.get().load(dados["urlImg1"] as String).into(binding.imageView)
                    }
                }
        }
    }

    fun fecharActivity() {
        this.finish()
    }

    fun retornaTodosUsuarios() {

        // pega tabela de usuários
        val referenciaUsuarios = fireStore.collection("usuarios")

        // filtros
        //  .whereEqualTo("nome", "Jp Reis")
        //  .whereNotEqualTo("nome", "Jp Reis")
        //  .whereIn("nome", listOf("Jp Reis", "Lana"))
        //  .whereArrayContains("NomeArray", "valor")
        //  .whereGreaterThan("idade", "10")
        //  .whereGreaterThanOrEqualTo("idade", "10")
        //  .whereLessThan("idade", "10")
        //  .whereLessThanOrEqualTo("idade", "10")
        //  .orderBy("idade", Query.Direction.ASCENDING)
        //  .orderBy("idade", Query.Direction.DESCENDING)

        referenciaUsuarios.addSnapshotListener { listUsuarios, error ->
            var relatorioUsuarios = ""
            listUsuarios?.forEach {

                val dadosUsuarioAtual = it.data
                val nome = dadosUsuarioAtual["nome"]
                val idade = dadosUsuarioAtual["idade"]

                relatorioUsuarios += "nome: $nome, idade: $idade\n"
            }

            binding.txtUidIni.text = relatorioUsuarios

            if (error != null) {
                binding.txtEmailIni.text = error.message
            }
        }
    }

    fun alterarDados() {
        val usuarioTeste2 = fireStore.collection("usuarios")
            .document("dshIQtCdS4dzAIR6vij08xKWhR22")

        // substitui o conteúdo desse registro
        val dadosAtualizados = mapOf("email" to "emailteste02att@gmail.com")
        usuarioTeste2.set(dadosAtualizados)

        // altera apenas o campo selecionado
        usuarioTeste2.update("email", "emailteste02att@gmail.com")

        // deleta da tabela
        usuarioTeste2.delete()
    }
}