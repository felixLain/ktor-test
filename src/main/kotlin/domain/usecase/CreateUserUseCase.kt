package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class CreateUserUseCase(private val repository: UserRepository) {
    // Business rules:
    // - name length >= 2
    // - valid email format
    // - email must be unique
    suspend operator fun invoke(user: User): User {
        val name = user.name.trim()
        if (name.length < 2) throw IllegalArgumentException("Name must be at least 2 characters")
        val email = user.email.trim().lowercase()
        if (!email.contains("@")) throw IllegalArgumentException("Invalid email format")
        val exists = repository.findByEmail(email)
        if (exists != null) throw IllegalArgumentException("Email already exists")
        return repository.create(user.copy(name = name, email = email))
    }
}