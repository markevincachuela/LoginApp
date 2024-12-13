package com.example.loginApp.domain

interface AuthRepository {

    suspend fun loginUser(): Boolean
}