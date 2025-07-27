package com.yong.blog.data.repository

import com.yong.blog.data.api.manager.ApiManager
import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.model.PostImage
import com.yong.blog.domain.repository.PostDetailRepository

class PostDetailRepositoryImpl(
    private val apiManager: ApiManager
): PostDetailRepository {
    override suspend fun getPostData(
        type: String,
        id: String
    ): PostData = apiManager.getPostData(type, id)

    override suspend fun getPostImage(
        type: String,
        id: String,
        srcID: String
    ): PostImage = apiManager.getPostImage(type, id, srcID)
}