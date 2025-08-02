package com.yong.blog.data.api.dto.response

import com.google.gson.annotations.SerializedName
import com.yong.blog.domain.model.PostData

data class PostDataResponse(
    @SerializedName("PostContent") val postContent: String,
    @SerializedName("PostDate") val postDate: String,
    @SerializedName("PostIsPinned") val postIsPinned: Boolean,
    @SerializedName("PostTag") val postTag: List<String>,
    @SerializedName("PostTitle") val postTitle: String,
    @SerializedName("PostURL") val postURL: String
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