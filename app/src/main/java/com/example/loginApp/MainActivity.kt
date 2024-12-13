package com.example.loginApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.loginApp.domain.User
import com.example.loginApp.ui_composed.home.HomeScreen
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseCrashlytics.getInstance().log(this.javaClass.simpleName)
        setContent {
            HomeScreen(user = User())
        }
    }
}
