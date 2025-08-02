package com.yong.blog.di

import com.yong.blog.data.api.manager.ApiManager
import com.yong.blog.data.api.manager.ApiManagerImpl
import com.yong.blog.data.api.service.ApiService
import com.yong.blog.data.repository.PostDetailRepositoryImpl
import com.yong.blog.data.repository.PostListRepositoryImpl
import com.yong.blog.domain.repository.PostDetailRepository
import com.yong.blog.domain.repository.PostListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideApiManager(
        apiService: ApiService
    ): ApiManager = ApiManagerImpl(apiService)

    @Provides
    @Singleton
    fun providePostListRepository(
        apiManager: ApiManager
    ): PostListRepository = PostListRepositoryImpl(apiManager)

    @Provides
    @Singleton
    fun providePostDetailRepository(
        apiManager: ApiManager
    ): PostDetailRepository = PostDetailRepositoryImpl(apiManager)
}