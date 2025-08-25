package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class GetAllUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): List<User> = repository.findAll()
}