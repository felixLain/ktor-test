package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class GetUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Int): User? = repository.findById(id)
}