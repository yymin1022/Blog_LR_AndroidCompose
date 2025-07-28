package com.yong.blog.di

import com.yong.blog.data.api.manager.ApiManager
import com.yong.blog.data.api.manager.ApiManagerImpl
import com.yong.blog.data.repository.PostDetailRepositoryImpl
import com.yong.blog.data.repository.PostListRepositoryImpl
import com.yong.blog.domain.repository.PostDetailRepository
import com.yong.blog.domain.repository.PostListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindApiManager(
        impl: ApiManagerImpl
    ): ApiManager

    @Binds
    @Singleton
    abstract fun bindPostListRepository(
        impl: PostListRepositoryImpl
    ): PostListRepository

    @Binds
    @Singleton
    abstract fun bindPostDetailRepository(
        impl: PostDetailRepositoryImpl
    ): PostDetailRepository
}