package com.yong.blog.di

import com.yong.blog.data.api.service.ApiService
import com.yong.blog.data.network.ApiServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiServiceFactory.createApiService()
}