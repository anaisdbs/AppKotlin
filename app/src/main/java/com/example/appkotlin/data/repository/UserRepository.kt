package com.example.appkotlin.data.repository

import com.example.appkotlin.data.local.DatabaseDao
import com.example.appkotlin.data.local.models.toData
import com.example.appkotlin.data.local.models.toEntity
import com.example.appkotlin.domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao
) {

    suspend fun createUser(user: User){
        databaseDao.insert(user.toData())

    }

    fun getUser(email: String) : User? {
        val userLocal = databaseDao.findByName(email)
        return userLocal?.toEntity()
    }
}