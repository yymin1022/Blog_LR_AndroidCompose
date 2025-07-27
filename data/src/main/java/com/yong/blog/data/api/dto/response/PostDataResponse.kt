package com.yong.blog.data.api.dto.response

import com.yong.blog.domain.model.PostData

data class PostDataResponse(
    val postContent: String,
    val postDate: String,
    val postIsPinned: Boolean,
    val postTag: List<String>,
    val postTitle: String,
    val postURL: String
)

fun PostDataResponse.toDomain(postID: String) =
    PostData(
        postContent = this.postContent,
        postDate = this.postDate,
        postID = postID,
        postIsPinned = this.postIsPinned,
        postTag = this.postTag,
        postTitle = this.postTitle,
        postUrl = this.postURL
    )