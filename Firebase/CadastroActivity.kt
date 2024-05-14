package com.systemskings.firstprojectfirebase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.systemskings.firstprojectfirebase.databinding.ActivityCadastroBinding
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.UUID

class CadastroActivity : AppCompatActivity() {

    private val binding: ActivityCadastroBinding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val autentication: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val fireStore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val storage: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }

    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgCad.setOnClickListener {
            getImageUri()
        }

        binding.btnCadastrar.setOnClickListener {
            val email = binding.txtEmailCad.text.toString()
            val senha = binding.txtSenhaCad.text.toString()
            val nome = binding.txtNome.text.toString()
            val idade = binding.txtIdade.text.toString()

            criarUsuario(email, senha, nome, idade)

            binding.txtEmailCad.text.clear()
            binding.txtSenhaCad.text.clear()
        }

        binding.btnCloseCad.setOnClickListener {
            if (autentication.currentUser != null) {
                autentication.signOut()
            }
            this.finish()
        }
    }

    fun criarUsuario(email: String, senha: String, nome: String, idade: String) {
        autentication.createUserWithEmailAndPassword(email, senha)
            .addOnSuccessListener { authResult ->
                val uid = authResult.user?.uid.toString()

                val upload: UploadTask = storage.getReference("usuarios")
                    .child(uid)
                    .child("fotos")
                    .child(UUID.randomUUID().toString())
                    .putFile(imageUri)

                upload.addOnSuccessListener { task ->
                    var uriImg = ""
                    task.metadata?.reference?.downloadUrl?.addOnSuccessListener { url ->
                        uriImg = url.toString()
                    }?.addOnCompleteListener {
                        val dados = mapOf(
                            "email" to email,
                            "nome" to nome,
                            "idade" to idade,
                            "urlImg1" to uriImg
                        )

                        fireStore.collection("usuarios")
                            .document(uid)
                            .set(dados)

                        Toast.makeText(this, "Sucesso ao registrar usuário.", Toast.LENGTH_LONG).show()
                    }
                }

            }.addOnFailureListener { exception ->
                Toast.makeText(this, "Erro ao registrar usuario: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    }

    private val abrirGaleria =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                binding.imgCad.setImageURI(uri)
                imageUri = uri
                Toast.makeText(this, "Imagem selecionada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nenhuma imagem selecionada", Toast.LENGTH_SHORT).show()
            }
        }

    fun getImageUri() = abrirGaleria.launch("image/jpeg")

    fun isFileSizeWithinLimit(uri: Uri, maxSizeInMB: Int): Boolean {
        val inputStream: InputStream = contentResolver.openInputStream(uri)!!
        val fileSizeInBytes = inputStream.available()
        val fileSizeInKB = fileSizeInBytes / 1024
        val fileSizeInMB = fileSizeInKB / 1024

        inputStream.close()
        return fileSizeInMB <= maxSizeInMB
    }

    fun compressImage(uri: Uri): ByteArray {
        // Abrir um InputStream a partir do Uri
        val inputStream: InputStream = contentResolver.openInputStream(uri)!!

        // Decodificar a imagem do InputStream
        val bitmap = BitmapFactory.decodeStream(inputStream)

        // Criar um novo fluxo de saída ByteArrayOutputStream
        val stream = ByteArrayOutputStream()

        // Comprimir o bitmap para o formato JPEG e 75% de qualidade
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream)

        return stream.toByteArray()
    }
}