package com.example.domain.repository

import com.example.domain.model.User

interface UserRepository {
    suspend fun create(user: User): User
    suspend fun findById(id: Int): User?
    suspend fun findByEmail(email: String): User?
    suspend fun update(user: User): User
    suspend fun delete(id: Int): Boolean
    suspend fun findAll(): List<User>
}