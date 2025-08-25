package com.example.config

import com.example.api.routes.registerUserRoutes
import com.example.domain.usecase.CreateUserUseCase
import com.example.domain.usecase.DeleteUserUseCase
import com.example.domain.usecase.GetAllUsersUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.UpdateUserUseCase
import io.ktor.server.application.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        registerUserRoutes(
            createUser = inject<CreateUserUseCase>().value,
            getUser = inject<GetUserUseCase>().value,
            listUsers = inject<GetAllUsersUseCase>().value,
            updateUser = inject<UpdateUserUseCase>().value,
            deleteUser = inject<DeleteUserUseCase>().value
        )
    }
}
