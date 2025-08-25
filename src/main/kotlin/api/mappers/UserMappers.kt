package com.example.api.mappers

import com.example.api.dto.CreateUserRequest
import com.example.api.dto.UpdateUserRequest
import com.example.api.dto.UserResponse
import com.example.domain.model.User

fun CreateUserRequest.toDomain(): User = User(name = name.trim(), email = email.trim().lowercase())
fun UpdateUserRequest.toDomain(id: Int): User = User(id = id, name = name.trim(), email = email.trim().lowercase())
fun User.toResponse(): UserResponse = UserResponse(id = id, name = name, email = email)
