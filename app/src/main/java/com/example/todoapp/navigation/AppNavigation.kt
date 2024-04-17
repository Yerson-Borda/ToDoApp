//App navigation
package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.screens.FirstScreen
import com.example.todoapp.screens.SecondScreen
import com.example.todoapp.screens.ThirdScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route){
        composable(route = AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route){
            SecondScreen(navController)
        }
        composable(
            route = AppScreens.ThirdScreen.route + "/{title}/{description}/{deadline}/{type}/{isChecked}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("deadline") { type = NavType.StringType },
                navArgument("type") { type = NavType.StringType },
                navArgument("isChecked") { type = NavType.BoolType } // New argument for checked state
            )
        ) { backStackEntry ->
            ThirdScreen(
                navController,
                title = backStackEntry.arguments?.getString("title") ?: "",
                description = backStackEntry.arguments?.getString("description") ?: "",
                deadline = backStackEntry.arguments?.getString("deadline") ?: "",
                type = backStackEntry.arguments?.getString("type") ?: "",
                isChecked = backStackEntry.arguments?.getBoolean("isChecked") ?: false // Receive checked state
            )
        }
    }
}