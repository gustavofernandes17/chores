package dev.gustavo.chores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import dev.gustavo.chores.ui.theme.ChoresTheme

// Importing Components (Screens)

import dev.gustavo.chores.components.LoginScreen
import dev.gustavo.chores.components.FeedScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChoresTheme {
                val navController = rememberNavController()

                // Application Navigation Host
                // For Development and Debugging reasons the authentication routes are also included here
                NavHost(navController = navController, startDestination = "authorization") {
                    composable("authorization") { LoginScreen(navController) }
                    composable("feed") { FeedScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChoresTheme {
        Greeting("Android")
    }
}