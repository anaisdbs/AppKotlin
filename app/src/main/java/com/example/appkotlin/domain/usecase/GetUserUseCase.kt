package com.example.appkotlin.domain.usecase

import com.example.appkotlin.data.repository.UserRepository
import com.example.appkotlin.domain.entity.User

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(email: String, password: String): User? {
        return userRepository.getUser(email, password)
    }
}