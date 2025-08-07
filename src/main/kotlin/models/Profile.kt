package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: Int,
    val name: String,
    val age: Int,
    val bio: String
)