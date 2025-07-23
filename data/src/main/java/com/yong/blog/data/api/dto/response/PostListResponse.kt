package com.yong.blog.data.api.dto.response

data class PostListResponse(
    val postCount: Int,
    val postList: List<PostDataResponse>
)