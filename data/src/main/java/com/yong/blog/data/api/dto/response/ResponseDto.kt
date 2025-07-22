package com.yong.blog.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    val RESULT_CODE: Int,
    val RESULT_MSG: String,
    val RESULT_DATA: T?
)

data class PostDataResponse(
    val postContent: String,
    val postDate: String,
    val postIsPinned: Boolean,
    val postTag: List<String>,
    val postTitle: String,
    val postURL: String
)

data class PostImageResponse(
    @SerializedName("ImageData") val base64Str: String
)

data class PostListResponse(
    val postCount: Int,
    val postList: List<PostDataResponse>
)
