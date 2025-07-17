package com.yong.blog.navigation

sealed class RouteDefinition(val route: String) {
    object Main: RouteDefinition("main")

    object List: RouteDefinition("list/{type}") {
        fun createRoute(type: String) = "list/$type"
    }

    object Detail: RouteDefinition("detail")
}