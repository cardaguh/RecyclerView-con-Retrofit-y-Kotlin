package com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.TestCoroutineRule
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.TestContextProvider
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.User
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.domain.Repo
import org.junit.*

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.NullPointerException

class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var repo: Repo

    @Mock
    private lateinit var usersObserver: Observer<Resource<List<User>>>

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(repo, TestContextProvider())
    }

    @After
    fun tearDown() {
        //        clear the references here
    }

    @Test
    fun fetchUsers() = testCoroutineRule.runBlockingTest {
        val databaseList = ArrayList<User>()
        val result = Resource.Success<List<User>>(databaseList)
        Mockito.`when`(repo.getUsersFromDatabase()).thenReturn(result)
        Mockito.`when`(repo.getUsers()).thenReturn(result)
        mainViewModel.fetchUsers()
            .observeForever(usersObserver)

        Mockito.verify(usersObserver, Mockito.times(3))
            .onChanged(Mockito.any<Resource.Loading<List<User>>>())
    }

    @Test
    fun fetchUsersFailure() = testCoroutineRule.runBlockingTest {
        Mockito.`when`(repo.getUsersFromDatabase()).thenThrow(NullPointerException())
        mainViewModel.fetchUsers()
            .observeForever(usersObserver)

        Mockito.verify(usersObserver, Mockito.times(2))
            .onChanged(Mockito.any<Resource.Loading<List<User>>>())
    }
}