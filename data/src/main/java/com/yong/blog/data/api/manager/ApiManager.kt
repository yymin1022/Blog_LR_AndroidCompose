package com.yong.blog.data.api.manager

import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.model.PostImage
import com.yong.blog.domain.model.PostList

interface ApiManager {
    suspend fun getPostData(postType: String, postID: String): PostData?
    suspend fun getPostImage(postType: String, postID: String, srcID: String): PostImage?
    suspend fun getPostList(postType: String): PostList?
}