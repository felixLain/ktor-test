package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class UpdateUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: User): User {
        val existing = repository.findByEmail(user.email)
        if (existing != null && existing.id != user.id) throw IllegalArgumentException("Email already in use")
        return repository.update(user)
    }
}