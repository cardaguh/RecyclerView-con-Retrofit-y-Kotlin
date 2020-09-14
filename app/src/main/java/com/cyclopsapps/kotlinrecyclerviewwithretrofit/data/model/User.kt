package com.cyclopsapps.kotlinrecyclerviewwithretrofit.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class User (
    val id:String,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey
    val uid:String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "userName")
    val username: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "website")
    val website: String
)


fun List<UserEntity>.toUserList(): List<User> = this.map {
    User(id = it.uid,name = it.name,username = it.username,email = it.email,phone = it.phone,website = it.website)
}

fun User.toUserEntity(): UserEntity = UserEntity(uid = id,name = name,username = username,email = email,phone = phone,website = website)