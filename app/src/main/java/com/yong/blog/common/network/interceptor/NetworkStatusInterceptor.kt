package com.yong.blog.common.network.interceptor

import android.content.Context
import android.util.Log
import com.yong.blog.common.exception.NetworkException
import com.yong.blog.common.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

class NetworkStatusInterceptor(
    private val context: Context
): Interceptor {
    companion object {
        private const val LOG_TAG = "NetworkInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!NetworkUtil.isNetworkAvail(context)) {
            Log.e(LOG_TAG, "Network Unavailable")
            throw NetworkException()
        }

        return chain.proceed(chain.request())
    }
}