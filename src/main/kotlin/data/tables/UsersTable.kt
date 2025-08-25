package com.example.data.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersTable : IntIdTable("users") {
    val name = varchar("name", 50)
    val email = varchar("email", 50).uniqueIndex()
}