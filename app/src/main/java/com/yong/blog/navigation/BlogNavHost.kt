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
            MainScreenNav(
                navigateToList = { type ->
                    navController.navigate(
                        route = BlogNavRoute.PostList(type)
                    )
                },
                navigateToDetail = { type, id ->
                    navController.navigate(
                        route = BlogNavRoute.PostDetail(type, id)
                    )
                }
            )
        }

        composable<BlogNavRoute.PostList> { backStack ->
            val route = backStack.toRoute<BlogNavRoute.PostList>()
            val postType = route.postType

            ListScreenNav(
                postType = postType,
                navigateToDetail = { type, id ->
                    navController.navigate(
                        route = BlogNavRoute.PostDetail(type, id)
                    )
                },
                navigateToMain = { navController.popBackStack() }
            )
        }

        composable<BlogNavRoute.PostDetail> { backStack ->
            val route = backStack.toRoute<BlogNavRoute.PostDetail>()
            val postType = route.postType
            val postID = route.postID

            DetailScreenNav(
                postType = postType,
                postID = postID,
                navigateToList = { navController.popBackStack() },
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

@Composable
private fun MainScreenNav(
    navigateToList: (String) -> Unit,
    navigateToDetail: (String, String) -> Unit
) {
    MainScreen(
        modifier = Modifier
            .fillMaxSize(),
        navigateToList = navigateToList,
        navigateToDetail = navigateToDetail
    )
}

@Composable
private fun ListScreenNav(
    postType: String,
    navigateToDetail: (String, String) -> Unit,
    navigateToMain: () -> Unit
) {
    ListScreen(
        modifier = Modifier
            .fillMaxSize(),
        postType = postType,
        navigateToDetail = navigateToDetail,
        navigateToMain = navigateToMain
    )
}

@Composable
private fun DetailScreenNav(
    postType: String,
    postID: String,
    navigateToList: () -> Unit,
    navigateToMain: () -> Unit
) {
    DetailScreen(
        modifier = Modifier
            .fillMaxSize(),
        postType = postType,
        postID = postID,
        navigateToList = navigateToList,
        navigateToMain = navigateToMain
    )
}