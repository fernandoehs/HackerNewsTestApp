package com.fernandoherrera.hackernewstestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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