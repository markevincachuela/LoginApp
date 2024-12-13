package com.example.loginApp

import com.example.loginApp.ui_composed.login.LoginScreen
import org.junit.Test

class LoginScreenUnitTest {


    @Test
    fun login() {
        val loginScreen = LoginScreen(
            email = "kevs@gmail.com",
            password = "1234",
            loginEnabled = true,
            loading = true,
            onEmailChanged =  Unit,
            onPasswordChanged = true
        ) {
            
        }
    }
}