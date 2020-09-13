package com.cyclopsapps.kotlinrecyclerviewwithretrofit.domain

import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.DataSource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.User

/**
 * Created by Carlos Daniel Agudelo on 13/09/2020.
 */
class RepoImpl(private val dataSource: DataSource) : Repo {

    override suspend fun getUsers(): Resource<List<User>> = dataSource.getUsers()
}