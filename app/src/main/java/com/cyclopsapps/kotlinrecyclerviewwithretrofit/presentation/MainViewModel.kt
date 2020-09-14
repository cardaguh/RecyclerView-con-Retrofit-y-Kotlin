package com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.domain.Repo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 * Created by Carlos Daniel Agudelo on 13/09/2020.
 */
class MainViewModel(private val repo: Repo) : ViewModel() {

    fun fetchUsers() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getUsersFromDatabase())
            emit(repo.getUsers())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class MainViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }

}
