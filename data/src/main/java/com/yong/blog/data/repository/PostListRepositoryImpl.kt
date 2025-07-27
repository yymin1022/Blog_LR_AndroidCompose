package com.yong.blog.data.repository

import com.yong.blog.data.api.manager.ApiManager
import com.yong.blog.domain.model.PostList
import com.yong.blog.domain.repository.PostListRepository

class PostListRepositoryImpl(
    private val apiManager: ApiManager
): PostListRepository {
    override suspend fun getPostList(type: String): PostList = apiManager.getPostList(type)
}