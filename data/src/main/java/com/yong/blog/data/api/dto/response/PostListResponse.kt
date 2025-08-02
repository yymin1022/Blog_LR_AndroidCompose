package com.yong.blog.data.api.dto.response

import com.google.gson.annotations.SerializedName
import com.yong.blog.domain.model.PostList
import com.yong.blog.domain.model.PostListItem

data class PostListResponse(
    @SerializedName("PostCount") val postCount: Int,
    @SerializedName("PostList") val postList: List<PostListItemResponse>
)

data class PostListItemResponse(
    val postDate: String,
    val postID: String,
    val postIsPinned: Boolean,
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