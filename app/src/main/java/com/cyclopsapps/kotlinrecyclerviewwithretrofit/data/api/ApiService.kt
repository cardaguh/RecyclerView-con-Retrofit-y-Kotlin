package com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.api

import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    suspend fun fetchAllUsers() : List<User>
}

object RetrofitService {
    val createService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}