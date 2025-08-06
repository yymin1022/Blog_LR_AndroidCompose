package com.yong.blog.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.yong.blog.detail.ui.DetailScreen
import com.yong.blog.list.ui.ListScreen
import com.yong.blog.main.ui.MainScreen

@Composable
fun BlogNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RouteDefinition.Main.route
    ) {
        composable(
            route = RouteDefinition.Main.route
        ) {
            MainScreen(
                modifier = Modifier
                    .fillMaxSize(),
                onNavigateToList = { postType ->
                    navController.navigate(RouteDefinition.List.createRoute(postType))
                }
            )
        }

        composable(
            route = RouteDefinition.List.route,
            arguments = listOf(
                navArgument("postType") { type = NavType.StringType }
            )
        ) { backStack ->
            val postType = backStack.arguments?.getString("postType") ?: throw Exception("Type Undefined")

            ListScreen(
                postType = postType,
                onNavigateToDetail = { postType, postID ->
                    navController.navigate(RouteDefinition.Detail.createRoute(postType, postID))
                },
                onNavigateToMain = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = RouteDefinition.Detail.route,
            arguments = listOf(
                navArgument("postType") { type = NavType.StringType },
                navArgument("postID") { type = NavType.StringType }
            )
        ) { backStack ->
            val postID = backStack.arguments?.getString("postID") ?: throw Exception("ID Undefined")
            val postType = backStack.arguments?.getString("postType") ?: throw Exception("Type Undefined")

            DetailScreen(
                postType = postType,
                postID = postID,
                onNavigateToList = {
                    navController.popBackStack()
                },
                onNavigateToMain = {
                    navController.popBackStack(
                        RouteDefinition.Main.route,
                        inclusive = false
                    )
                }
            )
        }
    }
}