package com.example.accenturecodeinterview.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.accenturecodeinterview.presentation.viewmodel.EmployerConfigViewModel


@Composable
fun AppNavHost(viewModel: EmployerConfigViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            EmployerListUI(navController, viewModel)
        }
        composable("detail/{employerId}") { backStackEntry ->
            val employerId = backStackEntry.arguments?.getString("employerId")?.toIntOrNull()
            employerId?.let {
                EmployerDetailScreen(navController, it, viewModel)
            }
        }
    }

}
