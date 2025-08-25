package com.example.api.utils


import io.ktor.server.application.*

fun ApplicationCall.getIdParam(name: String = "id"): Int? =
    parameters[name]?.toIntOrNull()
