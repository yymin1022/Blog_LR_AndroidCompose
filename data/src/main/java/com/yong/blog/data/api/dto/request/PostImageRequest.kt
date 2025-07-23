package com.yong.blog.data.api.dto.request

data class PostImageRequest(
    val postID: String,
    val postType: String,
    val srcID: String
)