package com.yong.blog.domain.model

data class PostData(
    val postID: String,
    val postContent: String,
    val postDate: String,
    val postIsPinned: Boolean,
    val postTag: List<String>,
    val postTitle: String,
    val postUrl: String
)
