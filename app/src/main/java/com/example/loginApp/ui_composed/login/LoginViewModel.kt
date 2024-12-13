package com.example.loginApp.ui_composed.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginApp.utils.SnackMessage
import com.example.loginApp.data.AuthRepositoryImpl
import com.example.loginApp.domain.User
import com.example.loginApp.domain.UserRepository
import com.example.loginApp.data.UserRepositoryImpl
import com.example.loginApp.domain.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl(),
    private val userRepository: UserRepository = UserRepositoryImpl()
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var loginEnabled by mutableStateOf(false)
        private set

    var loading by mutableStateOf(false)
        private set

    var isSignedIn: Boolean? by mutableStateOf(null)
        private set

    var snack: SnackMessage by mutableStateOf(SnackMessage())
        private set

    val user = userRepository.getUser()

    init {
        viewModelScope.launch {
            delay(1000)
            isSignedIn = false
        }
    }

    fun onSnackCompleted() {
        snack = snack.copy(message = null)
    }

    fun onLoginChanged(email: String, password: String) {
        this.email = email
        this.password = password
        if (!loading) loginEnabled = isValidEmail(email) && isValidPassword(password)
    }

    fun onLoginSelected() {
        loginEnabled = true
        viewModelScope.launch {
            loading = true
            loginEnabled = false
            val loggedIn = authRepository.loginUser()
            loginEnabled = isValidEmail(email) && isValidPassword(password)
            loading = false
            randomLogin(loggedIn)
            if (loggedIn) setUser(email)
        }
    }

    private fun setUser(email: String) {
        userRepository.setUser(User(email))
    }

    private fun randomLogin(loggedIn: Boolean) {
        if (loggedIn) {
            snack = snack.copy(message = "Login successful")
            isSignedIn = true
        } else {
            snack = snack.copy(id = snack.id.plus(1), message = "Couldn't login. Please try again.")
            isSignedIn = false
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length > 6
    }
}