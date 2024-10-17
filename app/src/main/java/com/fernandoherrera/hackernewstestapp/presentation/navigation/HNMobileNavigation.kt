package com.fernandoherrera.hackernewstestapp.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fernandoherrera.hackernewstestapp.presentation.ui.DetailScreen
import com.fernandoherrera.hackernewstestapp.presentation.ui.MainScreen

@Composable
fun HNMobileNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.MainScreen) {
        addMainScreen(navController)
    }
}

private fun NavGraphBuilder.addMainScreen(navController: NavHostController) {

    composable(NavRoutes.MainScreen) {
        MainScreen(
            onNewsClick = { url ->
                navController.navigate("detail_screen/${Uri.encode(url)}")
            },
            onDeletedNewsItem = {}
        )
    }
    composable(NavRoutes.DetailScreen) { backStackEntry ->
        val url = backStackEntry.arguments?.getString("url") ?: ""
        DetailScreen(url = url, navController = navController)

    }
}