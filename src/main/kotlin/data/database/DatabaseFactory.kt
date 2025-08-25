package com.example.data.database

import com.example.data.tables.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

//
//object DatabaseFactory {
//    fun init() {
//        val dbHost = System.getenv("DB_HOST") ?: "localhost"
//        val dbPort = System.getenv("DB_PORT") ?: "5432"
//        val dbName = System.getenv("DB_NAME") ?: "app_db"
//        val dbUser = System.getenv("DB_USER") ?: "user"
//        val dbPassword = System.getenv("DB_PASSWORD") ?: "password"
//
//        val jdbcUrl = "jdbc:postgresql://$dbHost:$dbPort/$dbName"
//
//        Database.connect(
//            url = jdbcUrl,
//            driver = "org.postgresql.Driver",
//            user = dbUser,
//            password = dbPassword
//        )
//
//        transaction {
//            SchemaUtils.create(UsersTable)
//        }
//    }
//}

object DatabaseFactory {
    fun init() {
        val dbHost = System.getenv("DB_HOST") ?: "localhost"
        val dbPort = System.getenv("DB_PORT") ?: "5432"
        val dbName = System.getenv("DB_NAME") ?: "app_database"
        val dbUser = System.getenv("DB_USER") ?: "app_user"
        val dbPassword = System.getenv("DB_PASSWORD") ?: "app_password"

        val jdbcUrl = "jdbc:postgresql://$dbHost:$dbPort/$dbName"

        var connected = false
        while (!connected) {
            try {
                Database.connect(
                    url = jdbcUrl,
                    driver = "org.postgresql.Driver",
                    user = dbUser,
                    password = dbPassword
                )
                connected = true
                println("✅ Connected to PostgreSQL")
            } catch (e: Exception) {
                println("Postgres not ready, retry in 2s...")
                Thread.sleep(2000)
            }
        }

        transaction {
            SchemaUtils.create(UsersTable)
            println("✅ Tables created")
        }
    }
}



