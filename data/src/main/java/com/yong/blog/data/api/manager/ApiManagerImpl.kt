package com.yong.blog.data.api.manager

import com.yong.blog.data.api.dto.request.PostDataRequest
import com.yong.blog.data.api.dto.request.PostImageRequest
import com.yong.blog.data.api.dto.request.PostListRequest
import com.yong.blog.data.api.dto.response.PostDataResponse
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

        return PostData(
            postID = postID,
            postContent = responseDto.RESULT_DATA.postContent,
            postDate = responseDto.RESULT_DATA.postDate,
            postIsPinned = responseDto.RESULT_DATA.postIsPinned,
            postTag = responseDto.RESULT_DATA.postTag,
            postTitle = responseDto.RESULT_DATA.postTitle,
            postUrl = responseDto.RESULT_DATA.postURL,
        )
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

        return PostImage(
            base64Str = responseDto.RESULT_DATA.base64Str
        )
    }

    override suspend fun getPostList(postType: String): PostList {
        val requestDto = PostListRequest(postType)
        val responseDto = api.getPostList(requestDto)

        if(responseDto.RESULT_CODE != 200
            || responseDto.RESULT_DATA == null) {
            TODO("Error Handling")
        }

        return PostList(
            postCount = responseDto.RESULT_DATA.postCount,
            postList = emptyList(),
        )
    }
}