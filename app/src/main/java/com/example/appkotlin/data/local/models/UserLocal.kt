package com.example.appkotlin.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.appkotlin.domain.entity.User

@Entity(indices = arrayOf(
    Index(value = ["email", "password"],
    unique = true)
))
data class UserLocal(
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
){
    @PrimaryKey(autoGenerate = true) var uid: Int? = null
}

fun User.toData() : UserLocal{
    return UserLocal(
        email =  email,
        password = password
    )
}

fun UserLocal.toEntity() : User{
    return User(
        email =  email,
        password = password
    )
}