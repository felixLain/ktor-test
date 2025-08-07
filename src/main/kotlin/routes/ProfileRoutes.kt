package com.example.routes

import com.example.models.Profile
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.profileRoutes() {
    val sampleProfiles = listOf(
        Profile(1, "Alice", 25, "I love dancing"),
        Profile(2, "Bob", 30, "I'm a musician")

    )

    get("/profiles") {
        call.respond(sampleProfiles)
    }

    get("/profiles/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        val profile = sampleProfiles.find { it.id == id }

        if (profile != null) {
            call.respond(profile)
        } else {
            call.respondText("Profile not found", status = HttpStatusCode.NotFound)
        }
    }
}
