package com.example.di

import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.CreateUserUseCase
import com.example.domain.usecase.DeleteUserUseCase
import com.example.domain.usecase.GetAllUsersUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.UpdateUserUseCase
import org.koin.dsl.module

val koinModule = module {
    single<UserRepository> { UserRepositoryImpl() }
    single { CreateUserUseCase(get()) }
    single { GetUserUseCase(get()) }
    single { GetAllUsersUseCase(get()) }
    single { UpdateUserUseCase(get()) }
    single { DeleteUserUseCase(get()) }
}
