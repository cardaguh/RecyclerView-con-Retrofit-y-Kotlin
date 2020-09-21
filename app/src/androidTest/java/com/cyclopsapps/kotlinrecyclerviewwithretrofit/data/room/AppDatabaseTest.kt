package com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.room

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java
        ).build()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun testSaveUsers() {
        CoroutineScope(Dispatchers.Main).launch {
            val user = UserEntity(
                "new",
                "abc",
                "abc123",
                "abc123@gmail.com",
                "+976765738929",
                "https://www.google.com/"
            )
            appDatabase.userDao().saveUser(user)
            val result = appDatabase.userDao().getAllUsers()
            Assert.assertEquals(1, result.size)

            Assert.assertEquals("abc123@gmail.com", result.firstOrNull()?.email)
        }
    }

}