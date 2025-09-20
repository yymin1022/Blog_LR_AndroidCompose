package com.yong.blog.data.repository

import com.yong.blog.data.api.manager.ApiManager
import com.yong.blog.domain.model.PostImage
import com.yong.blog.domain.model.PostList
import com.yong.blog.domain.repository.PostListRepository

class PostListRepositoryImpl(
    private val apiManager: ApiManager
): PostListRepository {
    companion object {
        private const val POST_THUMBNAIL_FILENAME = "thumb.png"
    }

    override suspend fun getPostList(type: String): PostList? = apiManager.getPostList(type)
    override suspend fun getPostThumbnail(type: String, id: String): PostImage? = apiManager.getPostImage(type, id, POST_THUMBNAIL_FILENAME)
}