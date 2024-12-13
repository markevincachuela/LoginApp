package com.example.loginApp.domain

import kotlinx.coroutines.flow.StateFlow

interface UserRepository {

    fun getUser(): StateFlow<User>

    fun setUser(user: User)
}

data class User(
    val email: String? = ""
)