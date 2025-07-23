package com.yong.blog.data.api.manager

import com.yong.blog.domain.model.PostData
import com.yong.blog.domain.model.PostImage
import com.yong.blog.domain.model.PostList

class ApiManagerImpl: ApiManager {
    override suspend fun getPostData(
        postType: String,
        postID: String
    ): PostData {
        TODO("Not yet implemented")
    }

    override suspend fun getPostImage(
        postType: String,
        postID: String,
        srcID: String
    ): PostImage {
        TODO("Not yet implemented")
    }

    override suspend fun getPostList(postType: String): PostList {
        TODO("Not yet implemented")
    }
}