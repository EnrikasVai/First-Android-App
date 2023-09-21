@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.testas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testas.ui.theme.TestasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestasTheme {
                val navController = rememberNavController()

                // Set up the NavHost with two composable screens
                NavHost(
                    navController = navController,
                    startDestination = "screen1"
                ) {
                    composable("screen1") {
                        ComposeCardApp(navController = navController)

                    }
                    composable("screen2") {
                        Screen2(navController = navController)
                    }
                    composable("screen3") {
                        Screen3(navController = navController)
                    }
                }
            }
        }
    }
}