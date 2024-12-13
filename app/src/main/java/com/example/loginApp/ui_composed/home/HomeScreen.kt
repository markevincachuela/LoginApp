package com.example.loginApp.ui_composed.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginApp.domain.User
import com.example.loginApp.ui_composed.login.LoginApp

@Composable
fun HomeScreen(
    user: User
) {
    val navController = rememberNavController()
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(it)
        ) {
            composable("home") {
                HomeScreenContent(
                    user = user,
                    navigateToLogin = {
                        navController.navigate("login")
                    }
                )
            }
            composable("login") {
                LoginApp()
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    user: User,
    navigateToLogin: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (user.email != "") {
                Text(text = "Welcome ${user.email}!")
                Button(onClick = navigateToLogin) {
                    Text(text = stringResource(id = com.example.loginApp.R.string.logout))
                }
            } else {
                Text(text = "Not logged")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = navigateToLogin) {
                    Text(text = stringResource(id = com.example.loginApp.R.string.login))

                }
            }
        }
    }
}