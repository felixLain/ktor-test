package com.example

import com.example.config.configureRouting
import com.example.config.configureSerialization
import com.example.data.database.DatabaseFactory
import com.example.di.koinModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    //DI
    install(Koin) {
        slf4jLogger()
        modules(koinModule)
    }

    DatabaseFactory.init()
    configureSerialization()
    configureRouting()
}
