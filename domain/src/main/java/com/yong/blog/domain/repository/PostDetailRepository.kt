package com.yong.blog.domain.repository

import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.model.PostImage

interface PostDetailRepository {
    suspend fun getPostData(type: String, id: String): PostData
    suspend fun getPostImage(type: String, id: String, srcID: String): PostImage
}