package com.yong.blog.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface BlogNavRoute {
    @Serializable
    data object Main: BlogNavRoute

    @Serializable
    data class PostList(
        val postType: String
    ): BlogNavRoute

    @Serializable
    data class PostDetail(
        val postType: String,
        val postID: String
    ): BlogNavRoute
}