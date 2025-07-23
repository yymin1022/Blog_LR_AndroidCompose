package com.yong.blog.data.api.service

import com.yong.blog.data.api.dto.request.*
import com.yong.blog.data.api.dto.response.*
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/getPostList")
    suspend fun getPostList(@Body req: PostListRequest): ResponseWrapper<PostListResponse>

    @POST("/getPostData")
    suspend fun getPostData(@Body req: PostDataRequest): ResponseWrapper<PostDataResponse>

    @POST("/getPostImage")
    suspend fun getPostImage(@Body req: PostImageRequest): ResponseWrapper<PostImageResponse>
}