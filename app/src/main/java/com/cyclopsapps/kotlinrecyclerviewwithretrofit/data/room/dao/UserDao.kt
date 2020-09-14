package com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.UserEntity

/**
 * Created by Carlos Daniel Agudelo on 14/09/2020.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers() : List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(userEntity: UserEntity)
}