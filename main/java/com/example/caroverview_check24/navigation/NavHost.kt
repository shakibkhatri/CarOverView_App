package com.example.caroverview_check24.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.caroverview_check24.view.DetailScreen
import com.example.caroverview_check24.view.OverviewScreen
import com.example.caroverview_check24.viewModel.CarViewModel

@Composable
fun AppNavHost(navController: NavHostController, viewModel: CarViewModel) {
    NavHost(navController = navController, startDestination = "overview") {
        composable("overview") {
            OverviewScreen(navController = navController, viewModel = viewModel)
        }
        composable("detail/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")
            if (carId != null) {
                DetailScreen(carId = carId, viewModel = viewModel)
            }
        }
    }
}