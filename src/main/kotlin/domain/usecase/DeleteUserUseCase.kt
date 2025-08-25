package com.example.domain.usecase

import com.example.domain.repository.UserRepository

class DeleteUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Int): Boolean = repository.delete(id)
}
