package com.cyclopsapps.kotlinrecyclerviewwithretrofit.data

import android.util.Log
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.api.ApiService
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Carlos Daniel Agudelo on 13/09/2020.
 */
class DataSource(private val apiService: ApiService) {

    suspend fun getUsers() : Resource<List<User>> {
        return Resource.Success(apiService.fetchAllUsers())
    }
}