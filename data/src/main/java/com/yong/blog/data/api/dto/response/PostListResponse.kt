package com.yong.blog.data.api.dto.response

import com.yong.blog.domain.model.PostList
import com.yong.blog.domain.model.PostListItem

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

fun PostListResponse.toDomain() =
    PostList(
        postCount = this.postCount,
        postList = this.postList.map { it.toDomain() }
    )

private fun PostListItemResponse.toDomain() =
    PostListItem(
        postDate = this.postDate,
        postID = this.postID,
        postIsPinned = this.postIsPinned,
        postTag = this.postTag,
        postTitle = this.postTitle,
        postURL = this.postURL
    )