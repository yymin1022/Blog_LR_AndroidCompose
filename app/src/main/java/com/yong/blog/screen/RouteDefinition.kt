package com.yong.blog.screen

sealed class RouteDefinition(val route: String) {
    object Main: RouteDefinition("main")
    object List: RouteDefinition("list")
    object Detail: RouteDefinition("detail")
}