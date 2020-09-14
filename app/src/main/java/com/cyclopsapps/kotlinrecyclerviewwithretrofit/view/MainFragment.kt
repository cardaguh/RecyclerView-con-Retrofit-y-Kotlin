package com.cyclopsapps.kotlinrecyclerviewwithretrofit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.R
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.core.Resource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.DataSource
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.api.RetrofitService
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model.User
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.room.AppDatabase
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.domain.RepoImpl
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation.MainViewModel
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation.MainViewModelFactory
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.presentation.adapter.UserAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    // activityViewModels para compartir con otros fragments
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(RepoImpl(DataSource(RetrofitService.createService, AppDatabase.getDatabase(requireActivity().applicationContext).userDao())))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewLifeCycleOwner si es fragment
        viewModel.fetchUsers().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    showData(it.data)
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showData(users: List<User>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter =
                UserAdapter(
                    users
                )
        }
    }
}