package com.cyclopsapps.kotlinrecyclerviewwithretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.R
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.DataSource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.User
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation.adapter.UserAdapter
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.api.ApiService
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.api.RetrofitService
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.domain.RepoImpl
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation.MainViewModel
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation.MainViewModelFactory
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

    }
}
