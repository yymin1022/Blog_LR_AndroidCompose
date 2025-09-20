package com.yong.blog.domain.repository

import com.yong.blog.domain.model.PostImage
import com.yong.blog.domain.model.PostList

interface PostListRepository {
    suspend fun getPostList(type: String): PostList?
    suspend fun getPostThumbnail(type: String, id: String): PostImage?
}