package com.fernandoherrera.hackernewstestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.fernandoherrera.hackernewstestapp.presentation.navigation.HNMobileNavigation
import com.fernandoherrera.hackernewstestapp.ui.theme.HackerNewsTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackerNewsTestAppTheme {
                val navHostController = rememberNavController()
                HNMobileNavigation(navController = navHostController)
            }
        }
    }
}