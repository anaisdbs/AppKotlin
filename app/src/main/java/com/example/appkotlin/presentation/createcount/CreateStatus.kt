package com.example.appkotlin.presentation.createcount


import android.provider.ContactsContract

sealed class CreateStatus

data class CreateSuccess(val email: String, val password: String): CreateStatus()
object CreateError : CreateStatus()