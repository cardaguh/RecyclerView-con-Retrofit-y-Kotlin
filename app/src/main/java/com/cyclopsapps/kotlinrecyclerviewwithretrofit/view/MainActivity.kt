package com.cyclopsapps.kotlinrecyclerviewwithretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.R
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.model.User
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.adapter.UserAdapter
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.api.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchAllUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("falla", "onFailures")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("exitoso", "onResponse: {${response.body()!![0].email}}")

                showData(response.body()!!)

            }

        })
    }

        private fun showData(users: List<User>){
            recyclerView.apply{
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter =
                    UserAdapter(
                        users
                    )
        }
    }
}
