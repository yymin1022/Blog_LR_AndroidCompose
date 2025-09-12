package com.yong.blog.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    @SerializedName("RESULT_CODE") val resultCode: Int,
    @SerializedName("RESULT_MSG") val resultMessage: String,
    @SerializedName("RESULT_DATA") val resultData: T?
)