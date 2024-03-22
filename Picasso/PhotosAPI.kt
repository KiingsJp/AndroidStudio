package com.kings.picasso

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosAPI {
    @GET("photos/{id}")
    fun buscarFoto(@Path("id") id: Int) : Call<FotoModel>
}