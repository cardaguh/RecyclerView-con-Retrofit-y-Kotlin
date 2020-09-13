package com.cyclopsapps.kotlinrecyclerviewwithretrofit.domain

import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.User

/**
 * Created by Carlos Daniel Agudelo on 13/09/2020.
 */
interface Repo {
    suspend fun getUsers() : Resource<List<User>>
}