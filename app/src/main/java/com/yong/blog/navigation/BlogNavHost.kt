package com.yong.blog.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
        startDestination = BlogNavRoute.Main
    ) {
        composable<BlogNavRoute.Main> {
            MainScreen(
                modifier = Modifier
                    .fillMaxSize(),
                navigateToList = { type ->
                    navController.navigate(
                        route = BlogNavRoute.PostList(type)
                    )
                }
            )
        }

        composable<BlogNavRoute.PostList> { backStack ->
            val postType = backStack.toRoute<BlogNavRoute.PostList>().postType

            ListScreen(
                modifier = Modifier
                    .fillMaxSize(),
                postType = postType,
                navigateToDetail = { type, id ->
                    navController.navigate(
                        route = BlogNavRoute.PostDetail(type, id)
                    )
                },
                navigateToMain = {
                    navController.popBackStack()
                }
            )
        }

        composable<BlogNavRoute.PostDetail> { backStack ->
            val postType = backStack.toRoute<BlogNavRoute.PostDetail>().postType
            val postID = backStack.toRoute<BlogNavRoute.PostDetail>().postID

            DetailScreen(
                modifier = Modifier
                    .fillMaxSize(),
                postType = postType,
                postID = postID,
                navigateToList = {
                    navController.popBackStack()
                },
                navigateToMain = {
                    navController.popBackStack(
                        route = BlogNavRoute.Main,
                        inclusive = false
                    )
                }
            )
        }
    }
}