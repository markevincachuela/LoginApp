package com.example.loginApp.data

import com.example.loginApp.domain.User
import com.example.loginApp.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepositoryImpl : UserRepository {

    private val _user = MutableStateFlow(User())
    private val user = _user.asStateFlow()

    override fun getUser(): StateFlow<User> = user

    override fun setUser(user: User) {
        _user.value = user
    }
}