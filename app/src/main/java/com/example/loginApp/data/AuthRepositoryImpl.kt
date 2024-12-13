package com.example.loginApp.data

import com.example.loginApp.domain.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl : AuthRepository {

    override suspend fun loginUser(): Boolean {
        delay(3000)
        return (0..1).shuffled().random() == 1
    }
}