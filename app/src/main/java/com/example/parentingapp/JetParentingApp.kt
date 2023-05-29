package com.example.parentingapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.parentingapp.ui.detail.ScoreDetailScreen
import com.example.parentingapp.ui.home.ScorePage
import com.example.parentingapp.ui.utils.Route

@Composable
fun JetParentingApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.Home.path,
        modifier = modifier
    ) {
        composable(Route.Home.path){
            ScorePage(
                navigateToDetail = { courseTitle ->
                    navHostController.navigate(Route.Detail.createRoute(courseTitle))
                }
            )
        }
        composable(Route.Detail.path, arguments = listOf(navArgument("courseTitle") {type = NavType.StringType}),
        ) {
            val title = it.arguments?.getString("courseTitle") ?: "Matematika"
            ScoreDetailScreen(courseTitle = title, navigateUp = { navHostController.navigateUp() })
        }
    }
}