package com.yong.blog.data.api.dto.response

data class PostDataResponse(
    val postContent: String,
    val postDate: String,
    val postIsPinned: Boolean,
    val postTag: List<String>,
    val postTitle: String,
    val postURL: String
)