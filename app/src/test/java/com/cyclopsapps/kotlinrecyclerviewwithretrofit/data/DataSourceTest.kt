package com.cyclopsapps.kotlinrecyclerviewwithretrofit.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.TestCoroutineRule
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.api.ApiService
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.User
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.toUserEntity
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.room.dao.UserDao
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DataSourceTest {
    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    lateinit var dataSource: DataSource

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var userDao: UserDao

    private val user = User(
        "new",
        "abc",
        "abc123",
        "abc123@gmail.com",
        "+976765738929",
        "https://www.google.com/"
    )


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataSource = DataSource(apiService, userDao)
    }

    @After
    fun tearDown() {
        //        clear the references here
    }

    @Test
    fun testGetUsersWithNoData() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(apiService.fetchAllUsers()).thenReturn(emptyList())
            Mockito.`when`(userDao.getAllUsers()).thenReturn(emptyList())
            val result = dataSource.getUsers() as Resource.Success
            Assert.assertEquals(0, result.data.size)
        }
    }

    @Test
    fun testGetUsersWithOneRecord() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(apiService.fetchAllUsers()).thenReturn(listOf(user))
            Mockito.`when`(userDao.getAllUsers()).thenReturn(listOf(user.toUserEntity()))
            val result = dataSource.getUsers() as Resource.Success
            Assert.assertEquals(1, result.data.size)
        }
    }

    @Test
    fun testGetUsersFromDatabaseWithNoData() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(userDao.getAllUsers()).thenReturn(emptyList())
            val result = dataSource.getUsersFromDatabase() as Resource.Success
            Assert.assertEquals(0, result.data.size)
        }
    }

    @Test
    fun testGUsersFromDatabaseOneRecord() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(userDao.getAllUsers()).thenReturn(listOf(user.toUserEntity()))
            val result = dataSource.getUsersFromDatabase() as Resource.Success
            Assert.assertEquals(1, result.data.size)
            Assert.assertEquals(user.email, "abc123@gmail.com")
            Assert.assertEquals(user.id, "new")
        }
    }
}