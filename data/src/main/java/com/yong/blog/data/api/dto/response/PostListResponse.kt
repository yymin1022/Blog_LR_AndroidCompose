package com.yong.blog.data.api.dto.response

data class PostListResponse(
    val postCount: Int,
    val postList: List<PostListItemResponse>
)

data class PostListItemResponse(
    val postDate: String,
    val postID: String,
    val postIsPinned: String,
    val postTag: List<String>,
    val postTitle: String,
    val postURL: String
)