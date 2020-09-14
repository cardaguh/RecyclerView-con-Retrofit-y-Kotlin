package com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.UserEntity
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.room.dao.UserDao

/**
 * Created by Carlos Daniel Agudelo on 14/09/2020.
 */
@Database(entities = arrayOf(UserEntity::class),version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE
                ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"tabla_users").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}