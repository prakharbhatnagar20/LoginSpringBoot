package com.example.springbootapptrial.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val success: Boolean, val message: String)

interface ApiService{
    @POST("user")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}

object RetrofitInstance{
    private const val BASE_URL = "https://f6c6-103-47-74-226.ngrok-free.app/"

    val api: ApiService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}