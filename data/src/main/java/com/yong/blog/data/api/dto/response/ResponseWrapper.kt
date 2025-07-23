package com.yong.blog.data.api.dto.response

data class ResponseWrapper<T>(
    val RESULT_CODE: Int,
    val RESULT_MSG: String,
    val RESULT_DATA: T?
)