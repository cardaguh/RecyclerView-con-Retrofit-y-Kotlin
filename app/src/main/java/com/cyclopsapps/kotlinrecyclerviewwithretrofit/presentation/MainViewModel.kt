package com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.CoroutineContextProvider
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.domain.Repo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 * Created by Carlos Daniel Agudelo on 13/09/2020.
 */
class MainViewModel(private val repo: Repo, private val contextProvider: CoroutineContextProvider) :
    ViewModel() {

    fun fetchUsers() = liveData(contextProvider.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getUsersFromDatabase())
            emit(repo.getUsers())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class MainViewModelFactory(private val repo: Repo, private val contextProvider: CoroutineContextProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java,CoroutineContextProvider::class.java).newInstance(repo,contextProvider)
    }

}
