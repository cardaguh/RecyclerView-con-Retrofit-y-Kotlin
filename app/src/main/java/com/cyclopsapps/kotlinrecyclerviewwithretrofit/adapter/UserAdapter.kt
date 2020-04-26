package com.cyclopsapps.kotlinrecyclerviewwithretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.R
import com.cyclopsapps.kotlinrecyclerviewwithretrofit.model.User
import kotlinx.android.synthetic.main.user_row.view.*

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.firstName.text = user.name
        holder.lastName.text = user.userName
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val firstName: TextView = itemView.firstName
        val lastName : TextView = itemView.secondName
    }
}