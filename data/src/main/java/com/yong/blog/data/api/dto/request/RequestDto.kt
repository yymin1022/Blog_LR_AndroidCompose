package com.yong.blog.data.api.dto.request

data class PostDataRequest(
    val postID: String,
    val postType: String
)

data class PostImageRequest(
    val postID: String,
    val postType: String,
    val srcID: String
)

data class PostListRequest(
    val postType: String
)