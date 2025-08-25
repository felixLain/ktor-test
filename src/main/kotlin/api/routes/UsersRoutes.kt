package com.example.api.routes

import com.example.api.dto.CreateUserRequest
import com.example.api.dto.UpdateUserRequest
import com.example.api.mappers.toDomain
import com.example.api.mappers.toResponse
import com.example.api.utils.getIdParam
import com.example.domain.usecase.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.registerUserRoutes(
    createUser: CreateUserUseCase,
    getUser: GetUserUseCase,
    listUsers: GetAllUsersUseCase,
    updateUser: UpdateUserUseCase,
    deleteUser: DeleteUserUseCase
) {
    route("/api/users") {
        get {
            call.respond(listUsers().map { it.toResponse() })
        }
        get("{id}") {
            val id = call.getIdParam() ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid id")
            getUser(id)?.let {
                call.respond(it.toResponse())
            } ?: call.respond(HttpStatusCode.NotFound)
        }
        post {
            val req = call.receive<CreateUserRequest>()
            val created = createUser(req.toDomain())
            call.respond(HttpStatusCode.Created, created.toResponse())
        }
        put("{id}") {
            val id = call.getIdParam() ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid id")
            val req = call.receive<UpdateUserRequest>()
            call.respond(updateUser(req.toDomain(id)).toResponse())
        }
        delete("{id}") {
            val id = call.getIdParam() ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid id")
            if (deleteUser(id)) call.respond(HttpStatusCode.NoContent)
            else call.respond(HttpStatusCode.NotFound)
        }
    }
}

