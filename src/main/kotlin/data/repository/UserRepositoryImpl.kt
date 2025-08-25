package com.example.data.repository

import com.example.data.tables.UsersTable
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImpl : UserRepository {

    override suspend fun create(user: User): User = withContext(Dispatchers.IO) {
        transaction {
            val id = UsersTable.insert {
                it[name] = user.name
                it[email] = user.email
            } get UsersTable.id
            user.copy(id = id.value)
        }
    }

    override suspend fun findById(id: Int): User? = withContext(Dispatchers.IO) {
        transaction {
            UsersTable.selectAll().where { UsersTable.id eq id }
                .map { it.toUser() }
                .singleOrNull()
        }
    }

    override suspend fun findByEmail(email: String): User? = withContext(Dispatchers.IO) {
        transaction {
            UsersTable.selectAll().where { UsersTable.email eq email }
                .map { it.toUser() }
                .singleOrNull()
        }
    }

    override suspend fun update(user: User): User = withContext(Dispatchers.IO) {
        transaction {
            UsersTable.update({ UsersTable.id eq user.id }) {
                it[name] = user.name
                it[email] = user.email
            }
        }
        user
    }

    override suspend fun delete(id: Int): Boolean = withContext(Dispatchers.IO) {
        val deletedCount = transaction {
            UsersTable.deleteWhere { UsersTable.id eq id }
        }
        deletedCount > 0
    }

    override suspend fun findAll(): List<User> = withContext(Dispatchers.IO) {
        transaction {
            UsersTable.selectAll().map { it.toUser() }
        }
    }

    private fun ResultRow.toUser(): User = User(
        id = this[UsersTable.id].value,
        name = this[UsersTable.name],
        email = this[UsersTable.email]
    )
}

