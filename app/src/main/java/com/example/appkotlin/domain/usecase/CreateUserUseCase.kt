package com.example.appkotlin.domain.usecase

import com.example.appkotlin.data.repository.UserRepository
import com.example.appkotlin.domain.entity.User

class CreateUserUseCase( //créer utilisateur
    private val userRepository: UserRepository
) {
    suspend fun invoke(user: User){ //coroutine fait les choses en assynchrone
    userRepository.createUser(user)
    }
}