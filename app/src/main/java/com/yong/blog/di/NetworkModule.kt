package com.yong.blog.di

import android.content.Context
import com.yong.blog.common.network.interceptor.NetworkStatusInterceptor
import com.yong.blog.data.api.service.ApiService
import com.yong.blog.data.network.ApiServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkStatusInterceptor(
        @ApplicationContext context: Context
    ): NetworkStatusInterceptor = NetworkStatusInterceptor(context)

    @Provides
    @Singleton
    fun provideApiService(
        networkStatusInterceptor: NetworkStatusInterceptor
    ): ApiService {
        val apiService = ApiServiceFactory.createApiService(
            listOf(networkStatusInterceptor)
        )

        return apiService
    }
}