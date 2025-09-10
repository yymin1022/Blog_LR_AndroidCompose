package com.yong.blog.data.api.manager

import com.yong.blog.data.api.dto.request.PostDataRequest
import com.yong.blog.data.api.dto.request.PostImageRequest
import com.yong.blog.data.api.dto.request.PostListRequest
import com.yong.blog.data.api.dto.response.toDomain
import com.yong.blog.data.api.service.ApiService
import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.model.PostImage
import com.yong.blog.domain.model.PostList

class ApiManagerImpl(private val api: ApiService): ApiManager {
    override suspend fun getPostData(
        postType: String,
        postID: String
    ): PostData {
        val requestDto = PostDataRequest(postID, postType)
        val responseDto = api.getPostData(requestDto)

        if(responseDto.RESULT_CODE != 200
            || responseDto.RESULT_DATA == null) {
            TODO("Error Handling")
        }

        return responseDto.RESULT_DATA.toDomain(postID)
    }

    override suspend fun getPostImage(
        postType: String,
        postID: String,
        srcID: String
    ): PostImage {
        val requestDto = PostImageRequest(postID, postType, srcID)
        val responseDto = api.getPostImage(requestDto)

        if(responseDto.RESULT_CODE != 200
            || responseDto.RESULT_DATA == null) {
            TODO("Error Handling")
        }

        return responseDto.RESULT_DATA.toDomain()
    }

    override suspend fun getPostList(postType: String): PostList {
        val requestDto = PostListRequest(postType)
        val responseDto = api.getPostList(requestDto)

        if(responseDto.RESULT_CODE != 200
            || responseDto.RESULT_DATA == null) {
            TODO("Error Handling")
        }

        return responseDto.RESULT_DATA.toDomain()
    }
}