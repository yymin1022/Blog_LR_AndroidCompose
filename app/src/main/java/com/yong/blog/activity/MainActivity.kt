package com.yong.blog.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yong.blog.screen.RouteDefinition
import com.yong.blog.screen.detail.DetailScreen
import com.yong.blog.screen.list.ListScreen
import com.yong.blog.screen.main.MainScreen
import com.yong.blog.ui.theme.Blog_LRTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val blogNavController = rememberNavController()

            Blog_LRTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BlogNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = blogNavController
                    )
                }
            }
        }
    }
}

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
        composable(RouteDefinition.Main.route) {
            MainScreen(
                onNavigateToList = { navController.navigate(RouteDefinition.List.route) }
            )
        }

        composable(RouteDefinition.List.route) {
            ListScreen(
                onNavigateToDetail = { navController.navigate(RouteDefinition.Detail.route) },
                onNavigateToMain = { navController.popBackStack() }
            )
        }

        composable(RouteDefinition.Detail.route) {
            DetailScreen(
                onNavigateToList = { navController.popBackStack() },
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