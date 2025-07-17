package com.yong.blog.navigation

sealed class RouteDefinition(val route: String) {
    object Main: RouteDefinition("main")

    object List: RouteDefinition("list/{postType}") {
        fun createRoute(postType: String) = "list/$postType"
    }

    object Detail: RouteDefinition("detail/{postType}/{postID}") {
        fun createRoute(postType: String, postID: String) = "detail/$postType/$postID"
    }
}