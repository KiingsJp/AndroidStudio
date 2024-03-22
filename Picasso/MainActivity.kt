package com.kings.picasso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kings.picasso.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// PICASSO
// implementation("com.squareup.picasso:picasso:2.8")

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        buscarFoto()
    }

    private fun buscarFoto() {

        RetrofitHelper.retrofit.create(PhotosAPI::class.java).buscarFoto(199)
            .enqueue(object : Callback<FotoModel> {

                override fun onResponse(call: Call<FotoModel>, response: Response<FotoModel>) {
                    if (response.isSuccessful) {
                        MainScope().launch {
                            val model = response.body()
                            binding.txt.text = model?.url
                            Picasso.get()
                                .load(model?.url)
                                /*.placeholder()  USADO PARA COLOCAR UMA IMAGEM ENQUANTO FAZ O CARREGAMENTO DA FOTO*/
                                .into(binding.imageView)
                        }
                    } else Log.i("PrintErroJp", "Erro Status Code: ${response.code()}")
                }

                override fun onFailure(call: Call<FotoModel>, t: Throwable) {
                    Log.i("PrintErroJp", "Erro api: ${t.message}")
                }
            })
    }
}